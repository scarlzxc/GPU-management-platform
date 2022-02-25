package com.manage.gpu.interceptor;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manage.gpu.entity.UserInfoDO;
import com.manage.gpu.utils.JWTUtil;
import com.manage.gpu.utils.LocalUser;
import com.manage.gpu.utils.RedisUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        //获取请求头中的令牌
        String token = request.getHeader("token");
        try {
            //验证令牌
            JWTUtil.verify(token);
            UserInfoDO user = JWTUtil.getUser(token);
            RedisUtils redisUtils = new RedisUtils();
            boolean b = (boolean) redisUtils.hget("jwt",token);
            if(user.getType().equals("student")||user.getType().equals("teacher")){
                //确定身份
                map.put("msg","没有权限");
                String json = new ObjectMapper().writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);
                return false;
            }else if(!b){
                //确定token是否还存在
                map.put("msg","token不存在");
                String json = new ObjectMapper().writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);
                return false;
            }
            LocalUser.USER.set(user);
            //放行请求
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg","无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效");
        }
        //设置状态
        map.put("state",false);
        //将map 转为json jackson 返回信息
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }



}

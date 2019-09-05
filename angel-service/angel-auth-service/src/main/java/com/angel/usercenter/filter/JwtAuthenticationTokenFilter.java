//package com.angel.usercenter.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.Resource;
//
//@Component
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Value("${jwt.header}")
//    private String tokenHeader;
//
//    @Value("${jwt.tokenHead}")
//    private String tokenHead;
//
//    @Value("${jwt.expiration}")
//    private Long expiration;
//
//    private RequestMatcher authenticationRequestMatcher;
//
//    public JwtAuthenticationTokenFilter() {
//
//    }
//
//    public JwtAuthenticationTokenFilter(RequestMatcher authenticationRequestMatcher) {
//        this.authenticationRequestMatcher = authenticationRequestMatcher;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        //过滤掉不需要token验证的url
//        if(authenticationRequestMatcher != null && !authenticationRequestMatcher.matches(httpServletRequest)){
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//            return;
//        }
//        String authHeader = httpServletRequest.getHeader(this.tokenHeader);
//        if (authHeader != null && authHeader.startsWith(tokenHead)) {
//            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
//            log.info("请求"+httpServletRequest.getRequestURI()+"携带的token值：" + authToken);
//            // 查看redis中的token信息是否过期
//            boolean isExists = redisUtil.hexists(RedisKeys.USER_KEY,authToken);
//            if (!isExists){
//                // token 过期 提示用户登录超时 重新登录系统
//                //throw  new MyAuthenticationException(ErrorCodeEnum.LOGIN_WITHOUT);
//            }
//
//            //如果在token过期之前触发接口,我们更新token过期时间，token值不变只更新过期时间
//            Date createTokenDate = jwtTokenUtil.getCreatedDateFromToken(authToken);  //获取token生成时间
//            log.info("createTokenDate: " + createTokenDate);
//            if(createTokenDate != null){
//                Duration between = Duration.between(Instant.now(), Instant.ofEpochMilli(createTokenDate.getTime()));
//                Long differSeconds = between.toMillis();
//                boolean isExpire = expiration > differSeconds;
//                if (isExpire) {  //如果 请求接口时间在token 过期之前 则更新token过期时间  我们可以将用户的token 存放到redis 中,更新redis 的过期时间
//
//                    //更新 延长 redis 中的过期时间
//
//
//                }
//            }
//
//
//            String useraccount = jwtTokenUtil.getUsernameFromToken(authToken);
//            log.info("JwtAuthenticationTokenFilter[doFilterInternal] checking authentication " + useraccount);
//
//            //token校验通过
//            if(useraccount != null){
//                TokenUtils.setToken(authToken);//设置token
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                if(authentication == null  || authentication.getPrincipal().equals("anonymousUser")){
//                    //根据account去数据库中查询user数据，足够信任token的情况下，可以省略这一步
//                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(useraccount);
//
//                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                        UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(
//                                userDetails, null, userDetails.getAuthorities());
//                        usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
//                                httpServletRequest));
//                        log.info("JwtAuthenticationTokenFilter[doFilterInternal]  authenticated user " + useraccount + ", setting security context");
//                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
//                    }
//                }else {
//                    log.info("当前请求用户信息："+ JSON.toJSONString(authentication.getPrincipal()));
//                }
//            }else {
//                log.info("token 无效.");
//                throw  new MyAuthenticationException(ErrorCodeEnum.TOKEN_INVALID);
//            }
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
package com.angel.auth.controller;

import com.angel.auth.constant.Constant;
import com.angel.auth.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;
/**
 * description:  登录控制器
 * @author ailikes
 * @date  19-10-9 16:50
 * @since 19-10-9
 **/
@Controller
public class LoginController {
    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    @Autowired
    private ApprovalStore approvalStore;
    @RequestMapping("/")
    public ModelAndView root(Map<String,Object> model, Principal principal){
        List<Approval> approvals=clientDetailsService.listClientDetails().stream()
                .map(clientDetails -> approvalStore.getApprovals(principal.getName(),clientDetails.getClientId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        model.put("approvals",approvals);
        model.put("clientDetails",clientDetailsService.listClientDetails());
        return new ModelAndView ("index",model);
    }

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value="/approval/revoke",method= RequestMethod.POST)
    public String revokApproval(@ModelAttribute Approval approval){
        approvalStore.revokeApprovals(Arrays.asList(approval));
        tokenStore.findTokensByClientIdAndUserName(approval.getClientId(),approval.getUserId())
                .forEach(tokenStore::removeAccessToken) ;
        return "redirect:/";
    }


    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request,HttpServletResponse response,@RequestParam(name = "t",required = false) String theme) {
        if(StringUtils.isNotBlank(theme)){
            Cookie cookie=new Cookie(Constant.THEME_KEY,theme);
            response.addCookie(cookie);
            CookieUtil.setCookie(response,Constant.THEME_KEY,theme);
            return "themes/"+theme+"/login";
        }else{
            String localTheme = CookieUtil.getCookieValue(request,Constant.THEME_KEY);
            if(StringUtils.isNotBlank(localTheme)){
                return "themes/"+localTheme+"/login";
            }else{
                return "themes/default/login";
            }
        }
    }




    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";}
}

package cn.downey.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("DsInfoCollectService")
public class DsInfoCollectService {

    @RequestMapping(value = "webInfoCollectService")
    public void webInfoCollectService(String json, HttpServletRequest request, HttpServletResponse response){
        System.out.println("come in");
    }
}

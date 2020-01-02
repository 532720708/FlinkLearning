package cn.downey.test;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;

@Controller
@RequestMapping("DsInfoCollectService")
public class DsInfoCollectService {

    @RequestMapping(value = "webInfoCollectService",method = RequestMethod.POST)
    public void webInfoCollectService(@RequestBody String json, HttpServletRequest request, HttpServletResponse response){
        System.out.println("come in");
        //业务开始

        //业务结束
        PrintWriter printWriter = getWriter(response);
        printWriter.write("success");
        closePrintWriter(printWriter);
    }

    private PrintWriter getWriter(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        OutputStream outputStream;
        PrintWriter printWriter = null;
        try{
            outputStream = response.getOutputStream();
            printWriter = new PrintWriter(outputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
        return printWriter;

    }

    private void closePrintWriter(PrintWriter printWriter){
        printWriter.flush();
        printWriter.close();

    }
}

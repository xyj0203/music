package com.example.music.Controller.AdminController;

import com.example.music.Service.ExcelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "管理员导出管理")
@RestController
@RequestMapping("/adminExcel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/user")
    public void getUserExcel(HttpServletResponse response) throws IOException {
        excelService.generateUserExcel(response);
    }
}

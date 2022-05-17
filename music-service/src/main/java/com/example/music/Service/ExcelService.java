package com.example.music.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface ExcelService {

    void generateUserExcel(HttpServletResponse response) throws IOException;
}

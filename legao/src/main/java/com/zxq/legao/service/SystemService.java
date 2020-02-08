package com.zxq.legao.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SystemService {
    String updateFieldsSet(String field, List<String> caption, HttpServletRequest request);

}

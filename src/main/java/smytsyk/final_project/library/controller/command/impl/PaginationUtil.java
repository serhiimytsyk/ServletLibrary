package smytsyk.final_project.library.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class PaginationUtil {
    public static<T> List<T> paginateList(List<T> list, int objects_per_page, HttpServletRequest req) {
        String pageStr = req.getParameter("page");
        if (pageStr == null || pageStr.isEmpty()) pageStr = "0";
        int page = Integer.parseInt(pageStr);

        req.getSession().setAttribute("pageNum", page);

        return list.stream().skip((long) page * objects_per_page).limit(objects_per_page).collect(Collectors.toList());
    }
}

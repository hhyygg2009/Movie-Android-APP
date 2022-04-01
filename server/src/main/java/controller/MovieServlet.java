package controller;

import bean.Movie;
import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

/**
 * @author hhyygg2009 on 2020/7/5.
 * @version 1.0
 */
public class MovieServlet extends HttpServlet {

    List<Movie> movie = null;

    boolean checkNotNull(HttpServletRequest req, String[] p) {
        for (String name : p) {
            if (req.getParameter(name) == null)
                return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        String root = req.getSession().getServletContext().getRealPath("/");
        if (checkNotNull(req, new String[]{"id"})) {
            movie = new Vector<>();
            int id = Integer.parseInt(req.getParameter("id"));
            movie.add(new MovieService().selectById(id));

        } else if (checkNotNull(req, new String[]{"title"})) {
            String name = req.getParameter("title");
            movie = new MovieService().selectByName(name);

        } else if (checkNotNull(req, new String[]{"lastest"})) {
            int count = Integer.parseInt(req.getParameter("lastest"));
            movie = new MovieService().selectLastest(count);
        } else if (checkNotNull(req, new String[]{"start", "count"})) {
            int start, count;
            start = Integer.parseInt(req.getParameter("start"));
            count = Integer.parseInt(req.getParameter("count"));
            movie = new MovieService().selectSection(start, count);
        }

        PrintWriter printWriter = resp.getWriter();
        if (movie != null) {
            printWriter.println(JSON.toJSON(movie));
        } else if (checkNotNull(req, new String[]{"cover"})) {
            int id = Integer.parseInt(req.getParameter("cover"));
            String upload = root + "upload/movie/cover/cover" + id + ".jpg";
            File pic = new File(upload);
            if (pic.exists()) {
                byte[] buffer = new byte[(int) pic.length()];
                if (new FileInputStream(pic).read(buffer) != -1)
                    printWriter.println(new Base64().encodeToString(buffer));

            }
        } else {
            printWriter.println("null");

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }
}

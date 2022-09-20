package edu.dsm.entity.po;

import java.util.List;

public class AddArticleWebPo {
    List<String> paths;
    String collegeName;

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public AddArticleWebPo(List<String> paths, String collegeName) {
        this.paths = paths;
        this.collegeName = collegeName;
    }
}

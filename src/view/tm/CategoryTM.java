package view.tm;

public class CategoryTM {
    private String cateName;
    private String cateId;

    public CategoryTM() {
    }

    public CategoryTM(String cateName, String cateId) {
        this.setCateName(cateName);
        this.setCateId(cateId);
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    @Override
    public String toString() {
        return "CategoryTM{" +
                "cateName='" + cateName + '\'' +
                ", cateId='" + cateId + '\'' +
                '}';
    }
}

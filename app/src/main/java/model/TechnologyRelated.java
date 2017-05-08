package model;

/**
 * Created by arvid.quarshie on 5/8/2017.
 * POJO object for the TechnologyRelated
 */

public class TechnologyRelated {
    private String title, Description;

    public TechnologyRelated() {
    }

    public TechnologyRelated(String title, String Description) {
        this.title = title;
        this.Description = Description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
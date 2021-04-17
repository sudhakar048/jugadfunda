package app.jugadfunda.login.news;

import java.util.ArrayList;
import app.jugadfunda.login.pojo.NewsPojo;

public interface NewsInterfaceView {

    void passDataToRecyclerView(ArrayList<NewsPojo> mNewsList);

    void checkforNodata();
}

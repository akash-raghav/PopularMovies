package raghav.akash.popularmovies;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewStub;

import butterknife.ButterKnife;
import butterknife.InjectView;
import raghav.akash.popularmovies.network.NetworkRequestHandler;

public class ToolbarActivity extends AppCompatActivity {

  @InjectView(R.id.toolbar) Toolbar toolbar;
  ViewStub contentViewStub;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (!NetworkRequestHandler.isSetup) {
      NetworkRequestHandler.setupNetworkHandler(getApplicationContext());
    }
  }

  @Override
  public void setContentView(@LayoutRes int layoutResID) {
    super.setContentView(R.layout.activity_toolbar);
    contentViewStub = (ViewStub) findViewById(R.id.content_view_Stub);
    contentViewStub.setLayoutResource(layoutResID);
    contentViewStub.inflate();
    ButterKnife.inject(this);
    setSupportActionBar(toolbar);
  }

  public void setTitle(String title) {
    toolbar.setTitle(title);
  }

  public void setTitle(int titleStringId) {
    toolbar.setTitle(titleStringId);
  }
}

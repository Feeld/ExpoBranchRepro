package host.exp.exponent;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expo.core.interfaces.Package;
import host.exp.exponent.generated.DetachBuildConstants;
import host.exp.exponent.experience.DetachActivity;
import io.branch.referral.Branch;
import io.branch.referral.Defines;

public class MainActivity extends DetachActivity {

  @Override
  public String publishedUrl() {
    return "exp://exp.host/@adoankim/BranchExpoTest";
  }

  @Override
  public String developmentUrl() {
    return DetachBuildConstants.DEVELOPMENT_URL;
  }

  @Override
  public List<String> sdkVersions() {
    return new ArrayList<>(Arrays.asList("30.0.0"));
  }

  @Override
  public List<ReactPackage> reactPackages() {
    return ((MainApplication) getApplication()).getPackages();
  }

  @Override
  public List<Package> expoPackages() {
    // Here you can add your own packages.
    return super.expoPackages();
  }

  @Override
  public boolean isDebug() {
    return BuildConfig.DEBUG;
  }

  @Override
  public Bundle initialProps(Bundle expBundle) {
    // Add extra initialProps here
    return expBundle;
  }

  // This fixes the issue
  /*
  @Override
  public void onNewIntent(Intent intent) {
    setIntent(intent);
    super.onNewIntent(intent);
  }
  */
}

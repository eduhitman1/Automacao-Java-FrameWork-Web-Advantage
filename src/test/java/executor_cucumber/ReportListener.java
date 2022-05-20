package executor_cucumber;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.edsoft.framework.utilities.ExtentReport;

public class ReportListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("On Test Start");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("On Test Sucess");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("On Test Failure");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("On Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("On Test Percentage");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("On Test Start");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("On Test Finish");
		ExtentReport.extentReports.flush();
	}

}

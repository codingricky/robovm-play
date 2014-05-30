package expense;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIBarButtonItem;
import org.robovm.apple.uikit.UIBarButtonItemStyle;
import org.robovm.apple.uikit.UIBarButtonSystemItem;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UINavigationController;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UITabBarController;
import org.robovm.apple.uikit.UITabBarItem;
import org.robovm.apple.uikit.UITableView;
import org.robovm.apple.uikit.UITableViewCell;
import org.robovm.apple.uikit.UITableViewCellStyle;
import org.robovm.apple.uikit.UITableViewController;
import org.robovm.apple.uikit.UITableViewDataSourceAdapter;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.uikit.UIWindow;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.BindSelector;
import org.robovm.objc.annotation.Method;
import org.robovm.rt.bro.annotation.Callback;

public class OneExpense extends UIApplicationDelegateAdapter {

    private UIWindow window = null;
	private UINavigationController tableNavigationController;
	private UITableViewController expenseTableViewController;

    @Override
    public boolean didFinishLaunching(UIApplication application,
            NSDictionary launchOptions) {
        expenseTableViewController = new UITableViewController();
        
        expenseTableViewController.getTableView().setDataSource(new UITableViewDataSourceAdapter() {

			@Override
			public long getNumberOfRowsInSection(UITableView tableView,
					long section) {
				return 3;
			}

			@Override
			public UITableViewCell getRowCell(UITableView tableView,
					NSIndexPath indexPath) {
				long row = indexPath.getIndexAt(1);
				if (row == 0) {
					UITableViewCell uiTableViewCell = new UITableViewCell(UITableViewCellStyle.Default, "hello");
					uiTableViewCell.setText("Category");
					return uiTableViewCell;
				} else {
					UITableViewCell uiTableViewCell = new UITableViewCell(UITableViewCellStyle.Default, "hello");
					uiTableViewCell.setText("Another Category");
					return uiTableViewCell;
				}
				
			}

			@Override
			public long getNumberOfSections(UITableView tableView) {
				return 2;
			}
        	
        });
        
        
        UIBarButtonItem barButtonItem = new UIBarButtonItem(UIBarButtonSystemItem.Add, null, null);
        Selector selector = Selector.register("add:");
		barButtonItem.setAction(selector);
		barButtonItem.setTarget(this);

		expenseTableViewController.setTitle("Testing");
		expenseTableViewController.getNavigationItem().setRightBarButtonItem(barButtonItem);
        
		tableNavigationController = new UINavigationController();
		tableNavigationController.addChildViewController(expenseTableViewController);
		
        UIViewController uiViewController2 = new UIViewController();
        uiViewController2.getView().setBackgroundColor(UIColor.colorGreen());
        uiViewController2.setTabBarItem(new UITabBarItem("Hello 2", 
        		UIImage.createFromBundle("resources/11-clock.png"), 
        		UIImage.createFromFile("resources/105-piano.png")));
        
        UITabBarController tabBarController = new UITabBarController();
        tabBarController.setViewControllers(new NSArray<UIViewController>(tableNavigationController, uiViewController2));
 
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        window.setRootViewController(tabBarController);
        window.setBackgroundColor(UIColor.colorLightGray());
        window.makeKeyAndVisible();
        
        return true;
    }
    
    @Method
    private void add(UIBarButtonItem item) {
//    	UINavigationController navigationController = new UINavigationController();
//		navigationController.setViewControllers(new NSArray<UIViewController>(expenseFormViewController));
    	ExpenseFormViewController expenseFormViewController = new ExpenseFormViewController(null);
    	tableNavigationController.pushViewController(expenseFormViewController, true);

    }
    
    public static void main(String[] args) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(args, null, OneExpense.class);
        pool.close();
    }
}
package expense;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.uikit.UIBarButtonItem;
import org.robovm.apple.uikit.UIBarButtonItemStyle;
import org.robovm.apple.uikit.UIKeyboardType;
import org.robovm.apple.uikit.UINavigationController;
import org.robovm.apple.uikit.UITableView;
import org.robovm.apple.uikit.UITableViewCell;
import org.robovm.apple.uikit.UITableViewCellAccessoryType;
import org.robovm.apple.uikit.UITableViewCellStyle;
import org.robovm.apple.uikit.UITableViewController;
import org.robovm.apple.uikit.UITableViewDataSourceAdapter;
import org.robovm.apple.uikit.UITableViewStyle;
import org.robovm.apple.uikit.UITextBorderStyle;
import org.robovm.apple.uikit.UITextField;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.BindSelector;
import org.robovm.objc.annotation.Method;
import org.robovm.rt.bro.annotation.Callback;

public class ExpenseFormViewController extends UITableViewController {
	
	private UINavigationController navigationController;
	
	public ExpenseFormViewController(UINavigationController navigationController) {
		super(UITableViewStyle.Grouped);
		this.navigationController = navigationController;
		setTitle("Expense");
		getTableView().setDataSource(new UITableViewDataSourceAdapter() {
			@Override
			public long getNumberOfRowsInSection(UITableView tableView,
					long section) {
				return 2;
			}
			
			@Override
			public long getNumberOfSections(UITableView tableView) {
				return 2;
			}

			@Override
			public UITableViewCell getRowCell(UITableView tableView,
					NSIndexPath indexPath) {
				UITableViewCell cell = new UITableViewCell(UITableViewCellStyle.Value1, "Test");
				cell.setAccessoryType(UITableViewCellAccessoryType.DisclosureIndicator);
				cell.setText("Amount");
				cell.getDetailTextLabel().setText("$10.00");
				cell.setTag(100);
				return cell;
			}
		});
		
		UIBarButtonItem doneButton = new UIBarButtonItem();
		doneButton.setTarget(this);
		doneButton.setStyle(UIBarButtonItemStyle.Done);
		doneButton.setAction(getDoneSelector());
		
		getNavigationItem().setRightBarButtonItem(doneButton);	
	}
	
	@Override
	public void didSelectRow(UITableView tableView, NSIndexPath indexPath) {
		
		UIViewController viewController = new UIViewController();
		viewController.setTitle("Enter Amount");
		UITextField textField = new UITextField();
		textField.setFrame(new CGRect(100, 100, 100, 20));
		textField.setKeyboardType(UIKeyboardType.DecimalPad);
		textField.setBorderStyle(UITextBorderStyle.RoundedRect);
		viewController.getView().addSubview(textField);
		viewController.getView().resizeToFit();
		
		getNavigationController().pushViewController(viewController, false);
	}

	@Override
	public void accessoryButtonTapped(UITableView tableView,
			NSIndexPath indexPath) {
		System.out.println(indexPath.getIndexAt(0));
	}

	public Selector getDoneSelector() {
		return Selector.register("expenseDone:");
	}
	
    @Method
    private void done(UIBarButtonItem item) {
		navigationController.popViewController(true);
    }
}

package contact;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import contact.model.Person;
import contact.view.PersonOverviewController;
import contact.view.PersonEditDialogController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
	 * ����ó�� ���� observable ����Ʈ
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * ������
	 */
	public MainApp() {
		// ���� �����͸� �߰��Ѵ�
		personData.add(new Person("ȫ", "�浿"));
		personData.add(new Person("��", "ö��"));
		personData.add(new Person("��", "����"));
		personData.add(new Person("��", "û��"));
		personData.add(new Person("��", "����"));
		personData.add(new Person("��", "����"));
		personData.add(new Person("��", "����"));
		personData.add(new Person("��", "����"));
		personData.add(new Person("��", "�ع�"));
	}

	/**
	 * ����ó�� ���� observable ����Ʈ�� ��ȯ�Ѵ�.
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}

    
	@Override
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ContactApp");

        initRootLayout();

        showPersonOverview();
	}

    /**
     * ���� ���̾ƿ��� �ʱ�ȭ�Ѵ�.
     */
    public void initRootLayout() {
        try {
            // fxml ���Ͽ��� ���� ���̾ƿ��� �����´�.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // ���� ���̾ƿ��� �����ϴ� scene�� �����ش�.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���� ���̾ƿ� �ȿ� ����ó ���(person overview)�� �����ش�.
     */
    public void showPersonOverview() {
        try {
            // ����ó ����� �����´�.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // ����ó ����� ���� ���̾ƿ� ����� �����Ѵ�.
            rootLayout.setCenter(personOverview);
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * ���� ���������� ��ȯ�Ѵ�.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	/**
	 * person�� �ڼ��� ������ �����ϱ� ���� ���̾�α׸� ����.
	 * ���� ����ڰ� OK�� Ŭ���ϸ� �־��� person�� ������ ������ �� true�� ��ȯ�Ѵ�.
	 *
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonEditDialog(Person person) {
	    try {
	        // fxml ������ �ε��ϰ� ���� ���ο� ���������� �����.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // ���̾�α� ���������� �����.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // person�� ��Ʈ�ѷ��� �����Ѵ�.
	        PersonEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setPerson(person);

	        // ���̾�α׸� �����ְ� ����ڰ� ���� ������ ��ٸ���.
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public static void main(String[] args) {
		launch(args);
	}
	

}


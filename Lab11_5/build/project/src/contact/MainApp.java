package contact;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import contact.model.Person;
import contact.model.PersonListWrapper;
import contact.view.PersonOverviewController;
import contact.view.RootLayoutController;
import contact.view.BirthdayStatisticsController;
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

        // (���� ���� �߰�) ���ø����̼� �������� �����Ѵ�.
        this.primaryStage.getIcons().add(new Image("file:resources/images/contact_book_512.png"));
        
        initRootLayout();

        showPersonOverview();
	}

	/**
	 * ���� ���̾ƿ��� �ʱ�ȭ�ϰ� ���������� ������ ����ó ������ �����´�.
	 */
	public void initRootLayout() {
	    try {
	        // fxml ���Ͽ��� ���� ���̾ƿ��� �����´�.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class
	                .getResource("view/RootLayout.fxml"));
	        rootLayout = (BorderPane) loader.load();

	        // ���� ���̾ƿ��� �����ϴ� scene�� �����ش�.
	        Scene scene = new Scene(rootLayout);
	        primaryStage.setScene(scene);

	        // ��Ʈ�ѷ����� MainApp ���� ������ �ش�.
	        RootLayoutController controller = loader.getController();
	        controller.setMainApp(this);

	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // ���������� ������ ����ó ������ �����´�.
	    File file = getPersonFilePath();
	    if (file != null) {
	        loadPersonDataFromFile(file);
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

	        dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
	        
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

	/**
     * ����ó ���� ȯ�漳���� ��ȯ�Ѵ�.
     * �� ������ ���������� ���� ���̰�, ȯ�漳���� OS Ư�� ������Ʈ���κ��� �д´�.
     * ���� preference�� ã�� ���ϸ� null�� ��ȯ�Ѵ�.
     *
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * ���� �ҷ��� ������ ��θ� �����Ѵ�. �� ��δ� OS Ư�� ������Ʈ���� ����ȴ�.
     *
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Stage Ÿ��Ʋ�� ������Ʈ�Ѵ�.
            primaryStage.setTitle("ContactApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Stage Ÿ��Ʋ�� ������Ʈ�Ѵ�.
            primaryStage.setTitle("ContactApp");
        }
    }
    
    /**
     * ������ ���Ϸκ��� ����ó �����͸� �����´�. ���� ����ó �����ͷ� ��ü�ȴ�.
     *
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // ���Ϸκ��� XML�� ���� ���� �� �������Ѵ�.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            // ���� ��θ� ������Ʈ���� �����Ѵ�.
            setPersonFilePath(file);

        } catch (Exception e) { // ���ܸ� ��´�
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("����");
            alert.setHeaderText("�����͸� ������ �� ����");
            alert.setContentText("file:" + file.getPath() + " ���� �����͸� ������ �� ����\n");

            alert.showAndWait();
        }
    }

    /**
     * ���� ����ó �����͸� ������ ���Ͽ� �����Ѵ�.
     *
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // ����ó �����͸� ���Ѵ�.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // ������ �� XML�� ���Ͽ� �����Ѵ�.
            m.marshal(wrapper, file);

            // ���� ��θ� ������Ʈ���� �����Ѵ�.
            setPersonFilePath(file);
        } catch (Exception e) { // ���ܸ� ��´�.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("����");
            alert.setHeaderText("�����͸� ������ �� ����");
            alert.setContentText("file:" + file.getPath() + " �� �����͸� ������ �� ����\n");

            alert.showAndWait();
        }
    }
    
    /**
     * ���� ��踦 �����ֱ� ���� ���̾�α׸� ����.
     */
    public void showBirthdayStatistics() {
        try {
            // FXML ������ �ҷ��ͼ� �˾��� ���ο� Stage�� �����.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // ����ó�� ��Ʈ�ѷ��� �����Ѵ�.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	public static void main(String[] args) {
		launch(args);
	}
	

}


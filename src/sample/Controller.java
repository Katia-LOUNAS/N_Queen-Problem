package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;


import static sample.GA.gaQueen;
import static sample.PSO1.PSOrecherche2;
import static sample.PSO1.genererTableauEtat;

public class Controller {


    @FXML
    private Button OP;


    @FXML
    private Slider Iter;

    @FXML
    private Label IterLabel;

    @FXML
    private Slider Rate;

    @FXML
    private Label RateLabel;

    @FXML
    private Slider popSize;

    @FXML
    private RadioButton A1BTN;

    @FXML
    private RadioButton A2BTN;

    @FXML
    private RadioButton BFS;

    @FXML
    private RadioButton DFS;

    @FXML
    private RadioButton GA;

    @FXML
    private RadioButton PSO;

    @FXML
    private Text Solu;

    @FXML
    private Text TempsEX;

    @FXML
    private Text TypeAlgo;

    @FXML
    private SplitPane chess;

    @FXML
    private Pane chessBoard;

    @FXML
    private Text nbrD;

    @FXML
    private Text nbrG;

    @FXML
    private AnchorPane pane;

    @FXML
    private Pane pane1;

    @FXML
    private TextField taille;

    public Noeud solu;

    public int size;

    private long timeElapsed;

    public String algo;


    public String nbrd;


    public String nbrg;


    public String sol=" ";


    public int Itera=100;


    public int populationSize=10;

    public Double rate= Double.valueOf(0.25F);


    public String lien_img ="C:/Users/HP.LAPTOP-ESRMF9MQ/IdeaProjects/NQueenProblem/src/sample/crown.png";




//-----------------------------------------------------------------------------------------
//                               A  METHODE
//-----------------------------------------------------------------------------------------

    @FXML
    void BbtnA1(ActionEvent event) {
        //Clear tout les radio bouton
        clearRadioButtons(A1BTN);
        //Déactiver le bouton Option
        OP.setVisible(false);
        //recuperation de la taille
        Stage mainWindow = (Stage) chess.getScene().getWindow();
        String t = taille.getText();
        size = Integer.parseInt(t);

        //afficher une alerte si la taille est tres petite
        if (size < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très  petite");

            alert.showAndWait();

        }

        //afficher une alerte si la taille est tres grande
        if (size > 12) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très  grande pour cet algorithme");

            alert.showAndWait();

        }

        //appel de A*
        int[] Action={0,0};// 0: la ligne , 1 : colonne
        int cout_chemin=0,profondeur=0;
        int n =size;
        int[] Etat=new int[n];

        Noeud racine = new Noeud(n, Etat, null, null, Action, cout_chemin, profondeur);

        //calcule du temps d'ex
        long startTime = System.nanoTime();
        Info resu = sample.A.heuristique(racine);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        solu= resu.getNode();

        //les infooooo
        nbrd= Integer.toString(resu.getNbr());
        nbrg= Integer.toString(resu.getNbrg());
        algo="Resolutipon avec l'algorithme A*";

        String s="";
        for(int i=0;i<size;i++){
            String elem=Integer.toString(resu.getNode().getT()[i]);
            s=s+" "+elem;
        }
        s=s+" ]";
        sol="[";
        sol=sol+s;


    }
//-----------------------------------------------------------------------------------------
//                               A  METHODE
//-----------------------------------------------------------------------------------------

    @FXML
    void btnA1(ActionEvent event) {
        //Clear tout les radio bouton
        clearRadioButtons(A2BTN);
        //Déactiver le bouton Option
        OP.setVisible(false);
        //recuperation de la taille
        Stage mainWindow = (Stage) chess.getScene().getWindow();
        String t = taille.getText();
        size = Integer.parseInt(t);

        //afficher une alerte si la tailleest tres pette
        if (size < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très  petite");

            alert.showAndWait();

        }

        //afficher une alerte si la taille est tres grande
        if (size > 12) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très  grande pour cet algorithme");

            alert.showAndWait();

        }

        //appel de A*
        int[] Action={0,0};// 0: la ligne , 1 : colonne
        int cout_chemin=0,profondeur=0;
        int n =size;
        int[] Etat=new int[n];

        Noeud racine = new Noeud(n, Etat, null, null, Action, cout_chemin, profondeur);

        //calcule du temps d'ex
        long startTime = System.nanoTime();
        Info resu= sample.A2.heuristiqueA2(racine);
        long endTime = System.nanoTime();
        timeElapsed = endTime - startTime;


        solu=resu.getNode();

        //les infooooo
        nbrd= Integer.toString(resu.getNbr());
        nbrg= Integer.toString(resu.getNbrg());
        algo="Resolutipon avec l'algorithme A*";

        String s="";
        for(int i=0;i<size;i++){
            String elem=Integer.toString(resu.getNode().getT()[i]);
            s=s+" "+elem;
        }
        s=s+" ]";
        sol="[";
        sol=sol+s;


    }
//-----------------------------------------------------------------------------------------
//                               BFS METHODE
//-----------------------------------------------------------------------------------------

    @FXML
    void btnBFS(ActionEvent event) {
        //Clear tout les radio bouton
        clearRadioButtons(BFS);
        //Déactiver le bouton Option
        OP.setVisible(false);
        //recuperation de la taille
        Stage mainWindow = (Stage) chess.getScene().getWindow();
        String t = taille.getText();
        size = Integer.parseInt(t);
        //afficher une alerte sila taille est tres petite
        if (size < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très  petite");

            alert.showAndWait();

        }
        //afficher une alerte si la taille est tres grande
        if (size > 12) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très  grande pour cet algorithme");

            alert.showAndWait();

        }
        //appel de bfs
        int[] Action={0,0};// 0: la ligne , 1 : colonne
        int cout_chemin=0,profondeur=0;
        int n =size;
        int[] Etat=new int[n];

        Noeud racine=new Noeud(n,Etat,null,null,Action,profondeur,cout_chemin);
        LinkedList<Noeud> listefifo = new LinkedList<Noeud>();
        LinkedList<Noeud> listefermé = new LinkedList<Noeud>();


        //calcul du temps d'ex
        long startTime = System.nanoTime();
        Info res= sample.BFS.ParcoursBFS(racine,listefifo,listefermé);
        long endTime = System.nanoTime();
        timeElapsed = endTime - startTime;

        solu=res.getNode();

        //les infooooo
        algo="Resolutipon avec l'algorithme BFS";
        nbrd= Integer.toString(res.getNbr());
        nbrg= Integer.toString(res.getNbrg());
        String s="";
        for(int i=0;i<size;i++){
            String elem=Integer.toString(res.getNode().getT()[i]);
            s=s+" "+elem;
        }
        s=s+" ]";
        sol="[";
        sol=sol+s;





    }


//-----------------------------------------------------------------------------------------
//                               DFS METHODE
//-----------------------------------------------------------------------------------------



    @FXML
    void btnDFS(ActionEvent event) {
        //Clear tout les radio bouton
        clearRadioButtons(DFS);
        //Déactiver le bouton Option
        OP.setVisible(false);
        algo="Resolutipon avec l'algorithme DFS";
        Stage mainWindow = (Stage) chess.getScene().getWindow();
        String t = taille.getText();
        size = Integer.parseInt(t);

        //afficher une alerte si ;a taille est tres petite
        if (size < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très petite");

            alert.showAndWait();

        }
        //afficher une alerte si la taille est tres grande
        if (size > 26) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très grande pour cet algorithme");

            alert.showAndWait();

        }

        //appel de dfs
        int[] Action={0,0};// 0: la ligne , 1 : colonne
        int cout_chemin=0,profondeur=0;
        int n =size;
        int[] Etat=new int[n];

        Noeud racine=new Noeud(n,Etat,null,null,Action,profondeur,cout_chemin);
        LinkedList<Noeud> listefifo = new LinkedList<Noeud>();
        LinkedList<Noeud> listefermé = new LinkedList<Noeud>();

        //calcul du temps d'ex
        long startTime = System.nanoTime();
        Info res=sample.DFS.ParcoursdFS(racine,listefifo,listefermé);
        long endTime = System.nanoTime();
        timeElapsed = endTime - startTime;

        solu=res.getNode();

        //les infooooo
        nbrd= Integer.toString(res.getNbr());
        nbrg= Integer.toString(res.getNbrg());

        String s="";
        for(int i=0;i<size;i++){
            String elem=Integer.toString(res.getNode().getT()[i]);
            s=s+" "+elem;
        }
        s=s+" ]";
        sol="[";
        sol=sol+s;


    }


    //-----------------------------------------------------------------------------------------
    //                               GA METHODE
    //-----------------------------------------------------------------------------------------
    @FXML
    void btnGA(ActionEvent event) {
        //Clear tout les radio bouton
        clearRadioButtons(GA);
        //Activer le bouton Option
        OP.setVisible(true);

        //------------------------------------------

        Stage mainWindow = (Stage) chess.getScene().getWindow();
        String t = taille.getText();
        size = Integer.parseInt(t);

        //afficher une alerte si ;a taille est tres petite
        if (size < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très petite");

            alert.showAndWait();

        }
        //afficher une alerte si la taille est tres grande
        if (size > 300) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très grande pour cet algorithme");

            alert.showAndWait();

        }

        // Appel GA
        ArrayList<Noeud> N= new ArrayList<Noeud>();
        int[] Etat=new int[size];
        int[] Act =new int[] {size-1, size-1};
        Noeud node =new Noeud(size,Etat , null, null, Act,  size-1, size-1);

        int popsize=10;
        Double mutation_rate=rate;
        int iterations=Itera;

        //calcul du temps d'ex
        long startTime = System.nanoTime();
        solu=gaQueen(popsize, node,mutation_rate,iterations);
        long endTime = System.nanoTime();
        timeElapsed = endTime - startTime;

        //les infooooo
        nbrd= null;
        nbrg= null;
        algo="Resolutipon avec l'algorithme GA";

        String s="";
        for(int i=0;i<size;i++){
            String elem=Integer.toString(solu.getT()[i]);
            s=s+" "+elem;
        }
        s=s+" ]";
        sol="[";
        sol=sol+s;



    }

    @FXML
    void btnPSO(ActionEvent event) {

        //Clear tout les radio bouton
        clearRadioButtons(PSO);
        //Déactiver le bouton Option
        OP.setVisible(true);

        //------------------------------------------

        Stage mainWindow = (Stage) chess.getScene().getWindow();
        String t = taille.getText();
        size = Integer.parseInt(t);

        //afficher une alerte si ;a taille est tres petite
        if (size < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très petite");

            alert.showAndWait();

        }
        //afficher une alerte si la taille est tres grande
        if (size > 300) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La taille de l'échiquier est très grande pour cet algorithme");

            alert.showAndWait();

        }

        // Appel PSO
        int  N = size;
        int  num_particles = populationSize;
        int max_iter = Itera;
        double w = rate;
        double c1 = 1;
        double c2 = 1;

        int[] gbest_position = new int[N];
        int[][] particles = new int[num_particles][N];
        int[][] velocity = new int[num_particles][N];
        int[] pbest_score = new int[num_particles];
        int[][] pbest_position = new int[num_particles][N];
        int gbest_score = Integer.MAX_VALUE;

        // generer  particle
        ArrayList<Noeud> particle = new ArrayList<>();

        for (int i = 0; i < num_particles; i++) {
            int[] etat = genererTableauEtat(N);
            // print(etat,etat.length);
            // print(etat,etat.length);
            Noeud noeud = new Noeud(N, etat, null, null, null, N-1, N-1);

            particle.add(noeud);
            for (int j = 0; j < N; j++) {

                velocity[i][j] = 0;
            }


            pbest_score[i] = Integer.MAX_VALUE;
        }

        //calcul du temps d'ex
        long startTime = System.nanoTime();
        gbest_position=PSOrecherche2(particle,velocity,pbest_score,pbest_position,gbest_score,gbest_position,max_iter,w,c1,c2);
        long endTime = System.nanoTime();
        timeElapsed = endTime - startTime;


        int[] Act =new int[] {size-1, size-1};
        Noeud node =new Noeud(size,gbest_position , null, null, Act,  size-1, size-1);
        solu=node;
        //les infooooo
        nbrd= null;
        nbrg= null;
        algo="Resolutipon avec l'algorithme PSO";

        String s="";
        for(int i=0;i<size;i++){
            String elem=Integer.toString(solu.getT()[i]);
            s=s+" "+elem;
        }
        s=s+" ]";
        sol="[";
        sol=sol+s;

    }

    @FXML
    void runbtn(ActionEvent event) {



        //affiche du resultat dans l'interface
        float rV = (float) (chessBoard.getWidth()/size);
        float rV1 = (float) (chessBoard.getHeight()/size);
        ObservableList<Node> items = chess.getItems();
        items.clear();

        // Create a new chessboard with the new size
        GridPane newChessboard = new GridPane();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle square = new Rectangle(rV, rV);
                if ((row + col) % 2 == 0) {
                    square.setFill(Color.web("#cfd0de"));
                } else {
                    square.setFill(Color.web("#293347"));
                }

                newChessboard.add(square, col, row);
                if(solu.getT()[row]==col){
                    Image icon = new Image(lien_img); // load the icon image
                    ImageView imageView = new ImageView(icon); // create an ImageView for the icon
                    imageView.setFitWidth(rV); // set the size of the icon
                    imageView.setFitHeight(rV1);
                    //square.setGraphic(imageView);
                    //StackPane stackPane = new StackPane(square, imageView);
                    newChessboard.add(imageView,col,row);
                }
            }
        }
        // Add the new chessboard to the pane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(newChessboard);

        // Set the alignment of the StackPane to center
        StackPane.setAlignment(newChessboard, Pos.BOTTOM_CENTER);

        // Add the StackPane to the SplitPane
        items.add(stackPane);

        //add les info


        TypeAlgo.setText(algo);
        if(nbrd==null){
            String nbrC= String.valueOf(solu.Conflit(solu,solu.getN()));
            nbrG.setText("Nombre de conflit: "+nbrC);
            nbrD.setText(" " );
        }
        else {
            nbrD.setText("Nombre de noeud developpés: " + nbrd);
            nbrG.setText("Nombre de noeud generés: " + nbrg);
        }
        Solu.setText("Solution: "+sol);

        String temp=Long.toString( (timeElapsed / 1000000));
        TempsEX.setText("Temps d'execution: "+ temp+" millisecondes");

        System.out.println(sol);


    }





    @FXML
    void Option(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OptionPopUp.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Changement de  Parameters");
        stage.show();
        Slider IterSlider = (Slider) scene.lookup("#Iter");
        Slider populationSlider = (Slider) scene.lookup("#popSize");
        Slider RateSlider = (Slider) scene.lookup("#Rate");
        Label IterLabel=(Label) scene.lookup("#IterLabel");
        Label RateLabel=(Label) scene.lookup("#RateLabel");
        Label populationLabel=(Label) scene.lookup("#populationLabel");
        DecimalFormat df=new DecimalFormat("#.##");

        IterSlider.setValue(Itera);
        populationSlider.setValue(populationSize);
        RateSlider.setValue(rate);
        IterLabel.setText(String.valueOf(Itera));
        populationLabel.setText(String.valueOf(populationSize));
        RateLabel.setText(df.format(rate) +"%");
        IterSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            Itera= newValue.intValue();
            IterLabel.setText(String.valueOf(Itera));


        });
        RateSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            rate= newValue.doubleValue();
            RateLabel.setText(df.format(rate) +"%");

        });
        populationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            populationSize= newValue.intValue();
            populationLabel.setText(String.valueOf(populationSize));

        });
        Button exit=(Button) scene.lookup("#OK");
        exit.setOnMouseClicked(e->{
            stage.close();
        });
    }






    private void clearRadioButtons(RadioButton id) {

        A1BTN.setSelected(false);
        A2BTN.setSelected(false);
        BFS.setSelected(false);
        GA.setSelected(false);
        PSO.setSelected(false);
        DFS.setSelected(false);
        id.setSelected(true);
    }

}

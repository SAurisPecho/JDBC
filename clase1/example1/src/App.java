public class App extends DAO {
    public static void main(String[] args) {
        App app = new App();
        try {
            //conectamos a la base de datos
            app.connectarDataBase();

            //desconectamos de la base de datos
            app.desconectarDataBase();

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
        }
    }
}

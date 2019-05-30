import com.grupo19.Models.Client;
import com.grupo19.Interfaces.IClient;


import java.util.Set;
import java.util.TreeSet;

public class CatClient {
    private Set<IClient> catalogo;

    public CatClient() {
        this.catalogo = new TreeSet<IClient>(new ComparatorClient());
    }

    public void addClient(Client a){
        this.catalogo.add(a.clone());
    }

}

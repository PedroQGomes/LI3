import com.grupo19.Client;
import com.grupo19.IClient;

import java.util.Set;
import java.util.TreeSet;

public class CatClient {
    private Set<IClient> catalogo;

    public CatClient() {
        this.catalogo = new TreeSet<>(new ComparatorClient());
    }

    public void addClient(Client a){
        this.catalogo.add(a.clone());
    }

}

import com.grupo19.Client;
import com.grupo19.IClient;

import java.util.Comparator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ComparatorClient implements Comparator<IClient> {

    public int compare(IClient a,IClient b){
        return a.getCodigo().compareTo(b.getCodigo());
    }

}

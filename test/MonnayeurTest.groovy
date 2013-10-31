import org.junit.Before
import org.junit.Test

class MonnayeurTest {
    private Monnayeur monnayeur

    @Before
    public void setUp() throws Exception {
        monnayeur = new Monnayeur()
    }

    @Test
    public void peutGérerLesPennies() {
        def possibilités = change(1)
        assert possibilités.size() == 1
        doitContenir(possibilités, [PENNIES : 1])
        doitContenir(change(2), [PENNIES : 2])
    }

    @Test
    public void peutGérerLesNickels() {
        doitContenir(change(5), [NICKELS : 1])
        doitContenir(change(10), [NICKELS : 2])
    }

    @Test
    public void peutGérerLesAutresCasDeBase() {
        doitContenir(change(10), [DIMES :1])
        doitContenir(change(25), [QUARTERS :1])
    }

    @Test
    public void peutRendreLaMonnaieSurPlusieursPièces() {
        doitContenir(change(6), [PENNIES: 1, NICKELS: 1])
    }

    private change(int cents) {
        monnayeur.change(cents)
    }

    void doitContenir(def change, def possibilité) {
        assert change.any { it == possibilité}
    }
}

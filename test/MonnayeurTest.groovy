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
        doitContenir(possibilités, [PENNIES: 1])
        doitContenir(change(2), [PENNIES: 2])
    }

    @Test
    public void peutDonnerLesNickels() {
        doitContenir(change(5), [NICKELS: 1])
    }

    @Test
    public void donneÉgalementLesPlusFaiblesPossibilités() {
        doitContenir(change(5), [PENNIES: 5])
    }

    @Test
    public void peutDonnerLaMonnaieSurPlusieursPièces() {
        doitContenir(change(6), [NICKELS: 1, PENNIES: 1])
    }

    @Test
    public void peutProposerPlusieursCombinatoiresQuandLaPieceRentrePlusieursFois() {
        doitContenir(change(10), [NICKELS: 1, PENNIES: 5])
    }

    @Test
    public void neGénèrePasTropDePossibilités() {
        assert change(2).size() == 1
    }

    @Test
    public void peutGérerLesDimes() {
        doitContenir(change(10), [DIMES: 1])
    }

    @Test
    public void peutGérerLesQuarters() {
        doitContenir(change(25), [QUARTERS: 1])
    }

    @Test
    public void peutGérerLeCasAttenduDuKata() {
        def possibilités = change(15)

        assert possibilités.size() == 6
        doitContenir(possibilités, [DIMES: 1, NICKELS: 1])
        doitContenir(possibilités, [DIMES: 1, PENNIES: 5])
        doitContenir(possibilités, [NICKELS: 3])
        doitContenir(possibilités, [NICKELS: 2, PENNIES: 5])
        doitContenir(possibilités, [NICKELS: 1, PENNIES: 10])
        doitContenir(possibilités, [PENNIES: 15])
    }

    private change(int cents) {
        monnayeur.change(cents)
    }

    void doitContenir(def change, def possibilité) {
        println("comparaison : $change")
        assert change.any { it == possibilité }
    }
}

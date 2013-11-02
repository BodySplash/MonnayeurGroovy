class Monnayeur {

    def change(cents) {
        Pieces.values().toList().reverse().inject([[combinaison: [:], reste: cents]]) {
            résultat, pièce ->
                découpe(pièce, résultat)
        }.collect {
            it.combinaison
        }


    }

    def découpe(pièce, possibilités) {
        possibilités.collectMany {
            possibilité ->
                if (pièce.vers(possibilité.reste) <= 0) {
                    return [possibilité]
                }
                if (pièce == Pieces.PENNIES) {
                    return [[combinaison: possibilité.combinaison.clone() << [(pièce.toString()): possibilité.reste], reste: 0]]
                }
                (1..pièce.vers(possibilité.reste)).collect([possibilité]) {
                    multiple ->
                         [combinaison: possibilité.combinaison.clone() << [(pièce.toString()): multiple], reste: possibilité.reste - multiple * pièce.valeur]
                }
        }
    }

    private enum Pieces {
        PENNIES(1), NICKELS(5), DIMES(10), QUARTERS(25)

        Pieces(int valeur) {
            this.valeur = valeur
        }

        public vers(cents) {
            (cents / valeur) as int
        }


        public final def valeur;
    }
}

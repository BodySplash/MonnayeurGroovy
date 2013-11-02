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
        possibilités.collectMany([], {
            if (pièce.vers(it.reste) > 0) {
                def combinatoirs = []
                if (pièce != Pieces.PENNIES) {
                    (1..pièce.vers(it.reste)).each {
                        multiple ->
                            combinatoirs << [combinaison: it.combinaison.clone() << [(pièce.toString()): multiple], reste: it.reste - multiple * pièce.valeur]
                    }
                    combinatoirs << [combinaison: it.combinaison.clone(), reste: it.reste]
                } else {
                    combinatoirs << [combinaison:  it.combinaison.clone() << [(pièce.toString()) : it.reste], reste: 0]
                }
                return combinatoirs
            }
            return [it]
        })
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

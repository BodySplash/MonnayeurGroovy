class Monnayeur {

    def change(cents) {
        def résultat = Pieces.values().inject([], {
            résultat, piece ->
                résultat << découpe(cents, piece)
                résultat
        })
        résultat.removeAll([:])
        résultat
    }

    private découpe(cents, piece) {
        if (cents >= piece.valeur) {
            def combinaison = [(piece.toString()): piece.vers(cents)]
            if (cents % piece.valeur > 0) {
                if (piece == Pieces.NICKELS) {
                    combinaison << [PENNIES: cents % piece.valeur]
                }
            }
            return combinaison
        }
        return [:]
    }



    private enum Pieces {
        PENNIES(1), NICKELS(5), DIMES(10), QUARTERS(25)


        Pieces(int valeur) {
            this.valeur = valeur
        }

        public vers(def cents) {
            (cents / valeur) as int
        }

        private final def valeur;
    }
}

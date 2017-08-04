package kreps.app.core

data class Representative (
        val name: String,
        val party: Party,
        val url: String,
        val address: Address
) {
}


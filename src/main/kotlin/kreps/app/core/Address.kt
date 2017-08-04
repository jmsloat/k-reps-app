package kreps.app.core

data class Address(

        val line1: String,
        val line2: String,
        val city: String,
        val state: String,
        val zip: Int
) {
    override fun toString(): String {
        return "${this.line1} ${this.line2} ${this.city} ${this.state} ${this.zip}"
    }

}
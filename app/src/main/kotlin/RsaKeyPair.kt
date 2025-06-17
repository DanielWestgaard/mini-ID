import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException

class RsaKeyPair() {

    fun generate(): KeyPair?{
        try {
            // Creating the object of KeyPairGenerator
            val keyPairGen: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
            println(keyPairGen)
            // Initializing with 2048
            keyPairGen.apply { initialize(2048) }
            // Generating the Key Pair
            val keyPair: KeyPair = keyPairGen.generateKeyPair()

            println("Public: ${keyPair.public}")
            println("Private: ${keyPair.private}")

            return keyPair
        }

        catch (e: NoSuchAlgorithmException) {
            println("Failed to Generate Keypair: $e")

            return null
        }
    }
}
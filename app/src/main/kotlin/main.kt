import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.callloging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*


// Main
fun main(){
    // print(RsaKeyPair().generate())
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configurePlugins()
        configureRouting()
    }.start(wait = true)
}

fun Application.configurePlugins() {
    install(ContentNegotiation) {
        json()
    }
    install((CallLogging))
}

// In-memory storage for demo and learning
val users = mutableListOf<User>()
var nextId = 1

fun Application.configureRouting() {
    routing {
        // GET all users
        get("/users") {
            if (users.isEmpty()) {
               call.respond(HttpStatusCode.NoContent, "User DB is empty")
            } else {
                call.respond(users)
            }
        }

        // GET user by ID
        get("/users/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }

            val user = users.find { it.id == id }
            if (user == null) {
                call.respond(HttpStatusCode.BadRequest, "User not found")
            } else {
                call.respond(user)
            }
        }

        // POST create user
        post("/user/create") {
            val request = call.receive<CreateUserRequest>()
            val newUser = User(
                id = nextId++,
                name = request.name,
                email = request.email
            )
            users.add(newUser)
            call.respond(HttpStatusCode.Created, newUser)
        }

        // POST update user
    }
}
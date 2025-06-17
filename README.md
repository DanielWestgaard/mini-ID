# mini-ID
An easy, but realistic Kotlin-based identity service that tries to imitate a simplified OpenID Connect-provider.

# Structure
```
.
├── README.md                                   # Description of this project
│
├── api/                                        # All API-endpoints, using ktor
│   ├── login.kt                                # POST /login - generates access_token and id_token
│   ├── userinfo.kt                             # GET /userinfo - userinfo if token is valid
│   ├── .well-known/
│   │   └── openid-configuration.kt             # GET //.well-known/openid-configuration - mocking of metadata
│   └── jwks.json                               # GET /jwks.json - public (RSA) key in JSON (for token verification)
│
├── rsa_keypairs.kt                             # Generating RSA keypair (once) during startup and caching
│
├── build.gradle.kts                            # Gradle configuration - Base structure 
└── .gradle                                     # Auto-generated from Base Structure
```

# Requirements
- `ktor`: Kotlin-based framework for building asynchronous, connected systems, including both client-side and server-side applications.
    - Eg. on Linux/Mac, run `brew install ktor`.

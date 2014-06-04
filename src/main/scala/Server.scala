import com.esotericsoftware.kryonet.{Server, Connection, Listener}

object Server extends App {
  val server = new Server()

  Network.register(server)

  server.addListener(new Listener() {
    override def connected(connection: Connection) {
      connection.sendTCP(new Hello)

      val (id, ip) = (connection.getID, connection.getRemoteAddressTCP)

      println(s"Client #$id connected from $ip")
    }

    override def disconnected(connection: Connection) {
      val id = connection.getID

      println(s"Client #$id disconnected...")
    }
  })

  server.bind(30401)
  server.start()

  println("Server started...")
}

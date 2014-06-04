import com.esotericsoftware.kryonet.{Connection, Listener, Client}
import com.esotericsoftware.kryonet.Listener.ThreadedListener
import java.io.IOException

object Main extends App {
  val client = new Client()
  Network.register(client)

  client.addListener(new ThreadedListener(new Listener() {
    override def received(connection: Connection, obj: Object) {
      if (obj.isInstanceOf[Hello]) println("Hello there")
    }

    override def disconnected(connection: Connection) {
      println("Disconnected from server...")

      System.exit(1)
    }
  }))

  try {
    client.start()
    client.connect(5000, "localhost", 30401)
  } catch {
    case e: IOException =>
      println(e.getMessage)
  }

  while (client.isConnected) Thread sleep 100
}

import com.esotericsoftware.kryonet.EndPoint


object Network {
  def register(endPoint : EndPoint) {
    val kryo = endPoint.getKryo

    kryo.register(classOf[Hello])
  }
}

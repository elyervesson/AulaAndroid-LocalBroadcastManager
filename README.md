# AulaAndroid-LocalBroadcastManager

Link da aula - https://youtu.be/WCjifxJ-skc

Explicação MainActivity enviará uma mensagem para o ServiceTest por meio do LocalBroadcast dele, o LocalBroadcastServiceTest;
           ServiceTest enviará uma mensagem para o FragmentThread por meio do LocalBroadcast dele, o LocalBroadcastFragmentThread;
           FragmentThread enviará uma mensagem para a ClasseDominio por meio do LocalBroadcast dela, o LocalBroadcastClasseDominio. Aqui vamos utilizar uma Thread de background;
           ClasseDominio enviará uma mensagem para a MainActivity por meio do LocalBroadcast dela, o LocalBroadcastMainActivity. Na MainActivity a mensagem final será apresentada em um TextView.
package com.cheitu.watch;

import java.util.concurrent.BlockingDeque;

import jpcap.PacketReceiver;
import jpcap.packet.Packet;

public class Receiver implements PacketReceiver {

	private BlockingDeque<Packet> netData;
	private BlockingDeque<Packet> request;
	private BlockingDeque<Packet> response;
	public Receiver(BlockingDeque<Packet> request,
			BlockingDeque<Packet> response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public void receivePacket(Packet packet) {

		try {
			if(packet.data.length > 0){
				if(Utils.isResponse(new String(packet.data,"utf-8")))
					response.add(packet);
				else
					if(Utils.isRequest(new String(packet.data,"utf-8")))
						request.add(packet);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

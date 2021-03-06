package iscx.cs.unb.ca.ifm.data;

import java.util.Arrays;

import iscx.cs.unb.ca.ifm.utils.IdGenerator;

import org.jnetpcap.packet.format.FormatUtils;

public class BasicPacketInfo {
	
/*  Basic Info to generate flows from packets  	*/
    private    long id;
    private    byte[] src;
    private    byte[] dst;
    private    int srcPort;
    private    int dstPort;
    private    int protocol;
    private    long   timeStamp;
    private    long   payloadBytes;
    private    String  flowId = null;  
/* ******************************************** */    
    private    boolean flagFIN = false;
    
	public BasicPacketInfo(byte[] src, byte[] dst, int srcPort, int dstPort,
			int protocol, long timeStamp, IdGenerator generator) {
		super();
		this.id = generator.nextId();
		this.src = src;
		this.dst = dst;
		this.srcPort = srcPort;
		this.dstPort = dstPort;
		this.protocol = protocol;
		this.timeStamp = timeStamp;
		generateFlowId();
	}
	
    public BasicPacketInfo(IdGenerator generator) {
		super();
		this.id = generator.nextId();
	}
    
    

	public String generateFlowId(){
    	boolean forward = true;
    	
//    	for(int i=0; i<this.src.length;i++){
//    		if(Byte.compare(this.src[i], this.dst[i])!=0){
//    			if(Byte.compare(this.src[i], this.dst[i])>0){
//    				forward = false;
//    			}
//    			i=this.src.length;
//    		}
//    	}    	 
    	for(int i=0; i<this.src.length;i++){           
    		if(Byte.toUnsignedInt(this.src[i])!=Byte.toUnsignedInt(this.dst[i])){
    			if(Byte.toUnsignedInt(this.src[i])>Byte.toUnsignedInt(this.dst[i])){
    				forward = false;
    			}
    			i=this.src.length;
    		}
    	}     	
    	
        if(forward){
            this.flowId = this.getSourceIP() + "-" + this.getDestinationIP() + "-" + this.srcPort  + "-" + this.dstPort  + "-" + this.protocol;
        }else{
            this.flowId = this.getDestinationIP() + "-" + this.getSourceIP() + "-" + this.dstPort  + "-" + this.srcPort  + "-" + this.protocol;
        }
        return this.flowId;
    }
    
	public String dumpInfo() {
		return null;
	}
          
    
    public String getSourceIP(){
    	return FormatUtils.ip(this.src);
    }

    public String getDestinationIP(){
    	return FormatUtils.ip(this.dst);
    }
    
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getSrc() {
		return src;
	}

	public void setSrc(byte[] src) {
		this.src = src;
	}

	public byte[] getDst() {
		return dst;
	}

	public void setDst(byte[] dst) {
		this.dst = dst;
	}

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public int getDstPort() {
		return dstPort;
	}

	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getFlowId() {
		return this.flowId!=null?this.flowId:generateFlowId();
	}

	public void setFlowId(String flowId) {		
		this.flowId = flowId;
	}

	public boolean isForwardPacket(byte[] sourceIP) {
		return Arrays.equals(sourceIP, this.src);
	}

	public long getPayloadBytes() {
		return payloadBytes;
	}

	public void setPayloadBytes(long payloadBytes) {
		this.payloadBytes = payloadBytes;
	}

	public boolean hasFlagFIN() {
		return flagFIN;
	}

	public void setFlagFIN(boolean flagFIN) {
		this.flagFIN = flagFIN;
	}
	
	
}

package dashuju.celloud.hadoop.demo2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * 	remote_ip string comment '请求发起的IP地址',
	temp1 string comment '占位符',
	temp2 string comment '占位符',
	time string comment 'OSS收到请求的时间',
	request_uri string comment '用户请求的URI',
	
	http_status string comment 'OSS返回的HTTP状态码',
	sent_bytes string comment '用户从OSS下载的流量',
	request_time string comment '完成本次请求的时间 毫秒',
	referer string comment '请求的HTTP Referer',
	user_agent string comment 'HTTP的User-Agent头',
	
	hostname string comment '请求访问域名',
	request_id string comment '用于唯一标识该请求的UUID',
	logging_flag string comment '是否开启了访问日志功能',
	aliyun_id string comment '请求者的阿里云ID 匿名访问为-',
	operation string comment '请求类型',
	
	bucket string comment '请求访问的Bucket名字',
	key string comment '用户请求的Key',
	object_size string comment 'Object大小',
	server_cost_time string comment 'OSS服务器处理本次请求所花的时间（毫秒）',
	error_code string comment 'OSS返回的错误码',
	
	request_length string comment '用户请求的长度Byte',
	user_id string comment 'Bucket拥有者ID',
	delta_datasize string comment 'Bucket大小的变化量,若没有变化为-',
	sync_request string comment '是否是CDN回源请求,若不是为-',
	temp3 string comment '占位符'
 * @author Administrator
 *
 */
public class OssLogWritable implements WritableComparable<OssLogWritable> {
	private String remoteIp;//请求发起的IP地址
	private String temp1;//占位符
	private String temp2;//占位符
	private String time;//OSS收到请求的时间
	private String requestUri;//用户请求的URI
	
	private String httpStatus;//OSS返回的HTTP状态码
	private String sentBytes;//用户从OSS下载的流量
	private String requestTime;//完成本次请求的时间 毫秒
	private String referer;//请求的HTTP Referer
	private String userAgent;//HTTP的User-Agent头
	
	private String hostName;//请求访问域名
	private String requestId;//用于唯一标识该请求的UUID
	private String loggingFlag;//是否开启了访问日志功能
	private String aliyunId;//请求者的阿里云ID 匿名访问为-
	private String operation;//请求类型
	
	private String bucket;//请求访问的Bucket名字
	private String key;//用户请求的Key
	private String objectSize;//Object大小
	private String serverCostTime;//OSS服务器处理本次请求所花的时间（毫秒）
	private String errorCode;//OSS返回的错误码
	
	private String requestLength;//用户请求的长度Byte
	private String userId;//Bucket拥有者ID
	private String deltaDatasize;//Bucket大小的变化量,若没有变化为-
	private String syncRequest;//是否是CDN回源请求,若不是为-
	private String temp3;//占位符
	
	//================implements Writable====================================
	public void write(DataOutput out) throws IOException {
		out.writeUTF(remoteIp);
		out.writeUTF(temp1);
		out.writeUTF(temp2);
		out.writeUTF(time);
		out.writeUTF(requestUri);
		
		out.writeUTF(httpStatus);
		out.writeUTF(sentBytes);
		out.writeUTF(requestTime);
		out.writeUTF(referer);
		out.writeUTF(userAgent);
		
		out.writeUTF(hostName);
		out.writeUTF(requestId);
		out.writeUTF(loggingFlag);
		out.writeUTF(aliyunId);
		out.writeUTF(operation);
		
		out.writeUTF(bucket);
		out.writeUTF(key);
		out.writeUTF(objectSize);
		out.writeUTF(serverCostTime);
		out.writeUTF(errorCode);
		
		out.writeUTF(requestLength);
		out.writeUTF(userId);
		out.writeUTF(deltaDatasize);
		out.writeUTF(syncRequest);
		out.writeUTF(temp3);
	}
	
	public void readFields(DataInput in) throws IOException {
		this.remoteIp = in.readUTF();
		this.temp1 = in.readUTF();
		this.temp2 = in.readUTF();
		this.time = in.readUTF();
		this.requestUri = in.readUTF();
		
		this.httpStatus = in.readUTF();
		this.sentBytes = in.readUTF();
		this.requestTime = in.readUTF();
		this.referer = in.readUTF();
		this.userAgent= in.readUTF();
		
		this.hostName = in.readUTF();
		this.requestId = in.readUTF();
		this.loggingFlag = in.readUTF();
		this.aliyunId = in.readUTF();
		this.operation = in.readUTF();
		
		this.bucket = in.readUTF();
		this.key = in.readUTF();
		this.objectSize = in.readUTF();
		this.serverCostTime = in.readUTF();
		this.errorCode = in.readUTF();
		
		this.requestLength = in.readUTF();
		this.userId = in.readUTF();
		this.deltaDatasize = in.readUTF();
		this.syncRequest = in.readUTF();
		this.temp3 = in.readUTF();
	}
	//=============compare=========================
	public int compareTo(OssLogWritable o) {
		return 0;
	}
	//==================setter  getter=========================
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getTemp1() {
		return temp1;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public String getTemp2() {
		return temp2;
	}
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getSentBytes() {
		return sentBytes;
	}
	public void setSentBytes(String sentBytes) {
		this.sentBytes = sentBytes;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getLoggingFlag() {
		return loggingFlag;
	}
	public void setLoggingFlag(String loggingFlag) {
		this.loggingFlag = loggingFlag;
	}
	public String getAliyunId() {
		return aliyunId;
	}
	public void setAliyunId(String aliyunId) {
		this.aliyunId = aliyunId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getObjectSize() {
		return objectSize;
	}
	public void setObjectSize(String objectSize) {
		this.objectSize = objectSize;
	}
	public String getServerCostTime() {
		return serverCostTime;
	}
	public void setServerCostTime(String serverCostTime) {
		this.serverCostTime = serverCostTime;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getRequestLength() {
		return requestLength;
	}
	public void setRequestLength(String requestLength) {
		this.requestLength = requestLength;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeltaDatasize() {
		return deltaDatasize;
	}
	public void setDeltaDatasize(String deltaDatasize) {
		this.deltaDatasize = deltaDatasize;
	}
	public String getSyncRequest() {
		return syncRequest;
	}
	public void setSyncRequest(String syncRequest) {
		this.syncRequest = syncRequest;
	}
	public String getTemp3() {
		return temp3;
	}
	public void setTemp3(String temp3) {
		this.temp3 = temp3;
	}
	//======================有参构造函数==========================
	public OssLogWritable(String remoteIp, String temp1, String temp2, String time, String requestUri,
			String httpStatus, String sentBytes, String requestTime, String referer, String userAgent, String hostName,
			String requestId, String loggingFlag, String aliyunId, String operation, String bucket, String key,
			String objectSize, String serverCostTime, String errorCode, String requestLength, String userId,
			String deltaDatasize, String syncRequest, String temp3) {
		super();
		this.remoteIp = remoteIp;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.time = time;
		this.requestUri = requestUri;
		this.httpStatus = httpStatus;
		this.sentBytes = sentBytes;
		this.requestTime = requestTime;
		this.referer = referer;
		this.userAgent = userAgent;
		this.hostName = hostName;
		this.requestId = requestId;
		this.loggingFlag = loggingFlag;
		this.aliyunId = aliyunId;
		this.operation = operation;
		this.bucket = bucket;
		this.key = key;
		this.objectSize = objectSize;
		this.serverCostTime = serverCostTime;
		this.errorCode = errorCode;
		this.requestLength = requestLength;
		this.userId = userId;
		this.deltaDatasize = deltaDatasize;
		this.syncRequest = syncRequest;
		this.temp3 = temp3;
	}
	//=================无参构造函数========================================
	public OssLogWritable() {
		super();
	}
	//==================hashcode====================================
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliyunId == null) ? 0 : aliyunId.hashCode());
		result = prime * result + ((bucket == null) ? 0 : bucket.hashCode());
		result = prime * result + ((deltaDatasize == null) ? 0 : deltaDatasize.hashCode());
		result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
		result = prime * result + ((httpStatus == null) ? 0 : httpStatus.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((loggingFlag == null) ? 0 : loggingFlag.hashCode());
		result = prime * result + ((objectSize == null) ? 0 : objectSize.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((referer == null) ? 0 : referer.hashCode());
		result = prime * result + ((remoteIp == null) ? 0 : remoteIp.hashCode());
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((requestLength == null) ? 0 : requestLength.hashCode());
		result = prime * result + ((requestTime == null) ? 0 : requestTime.hashCode());
		result = prime * result + ((requestUri == null) ? 0 : requestUri.hashCode());
		result = prime * result + ((sentBytes == null) ? 0 : sentBytes.hashCode());
		result = prime * result + ((serverCostTime == null) ? 0 : serverCostTime.hashCode());
		result = prime * result + ((syncRequest == null) ? 0 : syncRequest.hashCode());
		result = prime * result + ((temp1 == null) ? 0 : temp1.hashCode());
		result = prime * result + ((temp2 == null) ? 0 : temp2.hashCode());
		result = prime * result + ((temp3 == null) ? 0 : temp3.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((userAgent == null) ? 0 : userAgent.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	//===========equals=====================================
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OssLogWritable other = (OssLogWritable) obj;
		if (aliyunId == null) {
			if (other.aliyunId != null)
				return false;
		} else if (!aliyunId.equals(other.aliyunId))
			return false;
		if (bucket == null) {
			if (other.bucket != null)
				return false;
		} else if (!bucket.equals(other.bucket))
			return false;
		if (deltaDatasize == null) {
			if (other.deltaDatasize != null)
				return false;
		} else if (!deltaDatasize.equals(other.deltaDatasize))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (hostName == null) {
			if (other.hostName != null)
				return false;
		} else if (!hostName.equals(other.hostName))
			return false;
		if (httpStatus == null) {
			if (other.httpStatus != null)
				return false;
		} else if (!httpStatus.equals(other.httpStatus))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (loggingFlag == null) {
			if (other.loggingFlag != null)
				return false;
		} else if (!loggingFlag.equals(other.loggingFlag))
			return false;
		if (objectSize == null) {
			if (other.objectSize != null)
				return false;
		} else if (!objectSize.equals(other.objectSize))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (referer == null) {
			if (other.referer != null)
				return false;
		} else if (!referer.equals(other.referer))
			return false;
		if (remoteIp == null) {
			if (other.remoteIp != null)
				return false;
		} else if (!remoteIp.equals(other.remoteIp))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (requestLength == null) {
			if (other.requestLength != null)
				return false;
		} else if (!requestLength.equals(other.requestLength))
			return false;
		if (requestTime == null) {
			if (other.requestTime != null)
				return false;
		} else if (!requestTime.equals(other.requestTime))
			return false;
		if (requestUri == null) {
			if (other.requestUri != null)
				return false;
		} else if (!requestUri.equals(other.requestUri))
			return false;
		if (sentBytes == null) {
			if (other.sentBytes != null)
				return false;
		} else if (!sentBytes.equals(other.sentBytes))
			return false;
		if (serverCostTime == null) {
			if (other.serverCostTime != null)
				return false;
		} else if (!serverCostTime.equals(other.serverCostTime))
			return false;
		if (syncRequest == null) {
			if (other.syncRequest != null)
				return false;
		} else if (!syncRequest.equals(other.syncRequest))
			return false;
		if (temp1 == null) {
			if (other.temp1 != null)
				return false;
		} else if (!temp1.equals(other.temp1))
			return false;
		if (temp2 == null) {
			if (other.temp2 != null)
				return false;
		} else if (!temp2.equals(other.temp2))
			return false;
		if (temp3 == null) {
			if (other.temp3 != null)
				return false;
		} else if (!temp3.equals(other.temp3))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (userAgent == null) {
			if (other.userAgent != null)
				return false;
		} else if (!userAgent.equals(other.userAgent))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	//=================toString===============================
	@Override
	public String toString() {
		return remoteIp + "," + temp1 + "," + temp2 + "," + time + "," + requestUri + "," + httpStatus + "," + sentBytes
				+ "," + requestTime + "," + referer + "," + userAgent + "," + hostName + "," + requestId + ","
				+ loggingFlag + "," + aliyunId + "," + operation + "," + bucket + "," + key + "," + objectSize + ","
				+ serverCostTime + "," + errorCode + "," + requestLength + "," + userId + "," + deltaDatasize + ","
				+ syncRequest + "," + temp3;
	}
}

package ms2709.kafka.adapter.redis.ms2709.kafka.adapter.redis


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

/**
 *
 * 레디스 사용 관련 Util
 *
 * @class RedisOperator
 * @author 심문섭, ms2709@a2dcorp.co.kr
 * @version 1.0
 * @since 2022-12-08 오후 11:21
 * @modified
 */
@Component
class RedisOperator(
    private val redisTemplate: RedisTemplate<String, String>
) {

    /**
     * 기본 EXPIRE_SECOND
     */
    private val DEFAULT_EXPIRATION_PERIOD = Duration.ofSeconds(60L * 60L * 24L)

    /**
     * string 자료형을 다루기 위한 연산 객체
     */
    private val valueOp = redisTemplate.opsForValue()

    /**
     * set 자료형을 다루기 위한 연산 객체
     * field타입은 String으로 제한
     */
    private val setOp = redisTemplate.opsForSet()

    /**
     * SortedSet 자료형을 다루기 위한 연산객체
     * field타입은 String으로 제한
     */
    private val zSetOp = redisTemplate.opsForZSet()

    /**
     * list 자료형을 다루기 위한 연산객체
     * field타입은 String으로 제한
     */
    private val listOp = redisTemplate.opsForList()

    /**
     * hash 자료형을 다루기 위한 연산객체
     * hash 타입을 <String,String>으로 제한
     */
    private val hashOp = redisTemplate.opsForHash<String,String>()

    /**
     * String value set
     */
    fun set(key:String, value:String){
        valueOp.set(key, value)
    }

    fun set(key: String, value: String, timeout: Duration) {
        valueOp.set(key, value, timeout)
    }

    /**
     * String value get
     */
    fun get(key:String):String? = valueOp.get(key)?.toString()

    fun getAll(keyList:List<String>):List<String> = valueOp.multiGet(keyList)?.let { it.toList() } ?: emptyList()

    /**
     * String value get And Delete
     * (String 자료형을 pop처럼 사용하기 위함)
     */
    fun getAndDelete(key:String):String?{
        val value = valueOp.get(key) ?: return null
        this.delete(key)
        return value
    }

    /**
     * 해당 키가 이미 존재한다면 false 반환, 존재하지 않는다면 key:value 설정후에 true 반환
     */
    fun setIfAbsent(key:String, value:String, timeout: Duration = DEFAULT_EXPIRATION_PERIOD):Boolean {
        return valueOp.setIfAbsent(key, value, timeout)
    }

    fun increment(key:String):Long {
        return valueOp.increment(key)
    }

    /**
     * 공통 - delete key
     */
    fun delete(key:String):Boolean = redisTemplate.delete(key)

    /**
     * 공통 - exists key check
     */
    fun hasKey(key:String):Boolean = redisTemplate.hasKey(key)

    /**
     * 공통 - 해당 key에 대한 타임아웃 설정
     */
    fun setExpiration(key:String, timeout: Duration = DEFAULT_EXPIRATION_PERIOD):Boolean {
        return redisTemplate.expire(key, timeout)
    }

    /**
     * list value type
     * lpop
     */
    fun rightPop(key:String):String? = listOp.rightPop(key)

    fun rightPopAll(key: String): List<String> {
        val listSize = listOp.size(key)
        val result = listOp.rightPop(key, listSize?.toLong() ?: 0)

        return result ?: listOf()
    }




    /**
     * list value type
     * rpush
     */
    fun rightPush(key:String, value:String):Long? = listOp.rightPush(key,value)

    fun rightPushAll(key: String, vararg value: String) {
        listOp.rightPushAll(key, *value)
    }

    fun rightPushAll(key: String, values: Collection<String>) {
        listOp.rightPushAll(key, values)
    }

    /**
     * list value type
     * lpop
     */
    fun leftPop(key:String):String? = listOp.leftPop(key)

    fun leftPopAll(key: String): List<String> {
        val listSize = listOp.size(key)
        val result = listOp.leftPop(key, listSize?.toLong() ?: 0)

        return result ?: listOf()
    }

    /**
     * list value type
     * lpush
     */
    fun leftPush(key:String,value:String):Long? = listOp.leftPush(key, value)

    fun leftPushAll(key: String, vararg value: String) {
        listOp.leftPushAll(key, *value)
    }

    fun leftPushAll(key: String, values: Collection<String>) {
        if(values.isEmpty()){
            return
        }

        listOp.leftPushAll(key, values)
    }

    /**
     * list value type
     * get first
     */
    fun getListFirstValue(key:String):String? {
        return listOp.index(key,0)
    }

    /**
     * list value type
     * get last
     */
    fun getListLastValue(key:String):String?{
        return listOp.index(key, -1)
    }

    fun getAllListValues(key:String):List<String>{
        return listOp.range(key, 0, -1)?.toList() ?: listOf()
    }

    /**
     * list value type
     * get list value size
     */
    fun getListSize(key:String):Long?{
        return listOp.size(key)
    }

    /**
     * hash value type
     */
    fun setMap(key:String, map:Map<String,String>){
        hashOp.putAll(key, map)
    }

    /**
     * hash value type
     */
    fun setMapValue(key:String, mapKey:String, mapValue:String){
        hashOp.put(key,mapKey,mapValue)
    }

    /**
     * hash value type
     */
    fun getMap(key:String) : Map<String,String> {
        return hashOp.entries(key) ?: mapOf()
    }

    /**
     * hash value type
     */
    fun getMapValue(key:String,mapKey:String) :String?{
        return hashOp.get(key,mapKey)
    }

    /**
     * hash value type
     */
    fun deleteMapValue(key:String, mapKey:String):Long?{
        return hashOp.delete(key, mapKey)
    }

    /**
     * set value type
     *
     * redis command - sadd
     */
    fun addSetType(key:String, value:String):Long?{
        return setOp.add(key, value)
    }

    /**
     * set value type
     *
     * addAll
     */
    fun addAllSetType(key:String, values:List<String>){
        values.forEach {
            this.addSetType(key, it)
        }
    }

    /**
     * set value type
     * redis command - members
     */
    fun getSetTypeValues(key:String):List<String>{
        return setOp.members(key)?.toList() ?: listOf()
    }

    /**
     * set value type
     * redis command - scard
     */
    fun getSetTypeSize(key:String):Long?{
        return setOp.size(key)
    }

    /**
     * ser value type
     * redis command - srem
     */
    fun removeSetTypeElement(key:String, value:String):Long?{
        return setOp.remove(key, value)
    }

    /**
     * set value  type
     * redis command - sunion
     */
    fun unionSetTypes(key1:String, key2:String):Set<String>{
        return setOp.union(key1, key2)?.toSet() ?: return setOf()
    }

    /**
     * sortedSet value type
     *
     * redis command - zadd
     */
    fun addSortedSet(key:String, value:String, score:Int):Boolean?{
        return zSetOp.add(key, value, score.toDouble())
    }

    /**
     * sortedSet value type
     *
     * addAll
     */
    fun addAllSortedSet(key:String, values:List<Pair<String,Int>>){
        values.forEach { this.addSortedSet(key, it.first, it.second) }
    }

    /**
     * sortedSet value type
     *
     * redis command - zrank
     */
    fun getRank(key:String,value:String):Long?{
        return zSetOp.rank(key, value)
    }

    /**
     * sortedSet value type
     *
     * redis command - zrevrank
     */
    fun getReverseRank(key:String,value:String):Long?{
        return zSetOp.reverseRank(key, value)
    }

    /**
     * sortedSet value type
     *
     * redis command - zcount,
     * (zero base)
     */
    fun getRankCount(key:String, start:Int, end:Int):Long?{
        return zSetOp.count(key, start.toDouble(), end.toDouble())
    }

    /**
     * sortedSet value type
     *
     * 상위 count 만큼의 value 조회
     */
    fun getTopRankValues(key:String, count:Int):MutableSet<String>?{
        return zSetOp.range(key,0L, count.toLong())
    }
}
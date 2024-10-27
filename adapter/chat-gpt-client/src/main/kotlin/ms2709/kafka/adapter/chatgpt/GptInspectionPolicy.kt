package ms2709.kafka.adapter.chatgpt

interface GptInspectionPolicy {
    val instruction:String
    val example:String
    val exampleResult:String
}
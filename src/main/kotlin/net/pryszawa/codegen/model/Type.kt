package net.pryszawa.codegen.model

enum class Type(
    var allowFormats: Collection<Format?> = setOf(),
) {
    `object`,
    array,
    string(allowFormats = setOf(null, Format.byte, Format.binary, Format.date, Format.`date-time`, Format.password)),
    integer(allowFormats = setOf(Format.int32, Format.int64)),
    number(allowFormats = setOf(Format.float, Format.double)),
    boolean
}

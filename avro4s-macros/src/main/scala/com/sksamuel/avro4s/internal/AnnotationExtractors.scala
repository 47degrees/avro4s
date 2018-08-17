package com.sksamuel.avro4s.internal

import com.sksamuel.avro4s.{AvroAlias, AvroDoc, AvroNamespace}

class AnnotationExtractors(annos: Seq[Anno]) {

  private def findFirst(clazz: Class[_]): Option[String] = annos.find(_.name == clazz.getName).map(_.args.head.toString)
  private def findAll(clazz: Class[_]): Seq[String] = annos.filter(_.name == clazz.getName).map(_.args.head.toString)

  def namespace: Option[String] = findFirst(classOf[AvroNamespace])
  def doc: Option[String] = findFirst(classOf[AvroDoc])
  def aliases: Seq[String] = findAll(classOf[AvroAlias])
}
/*
 * Copyright (c) "Neo4j"
 * Neo4j Sweden AB [https://neo4j.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.spark.writer

import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.connector.write.DataWriter
import org.apache.spark.sql.connector.write.DataWriterFactory
import org.apache.spark.sql.connector.write.PhysicalWriteInfo
import org.apache.spark.sql.types.StructType
import org.neo4j.spark.util.Neo4jOptions

class Neo4jDataWriterFactory(
  jobId: String,
  structType: StructType,
  saveMode: SaveMode,
  options: Neo4jOptions,
  scriptResult: java.util.List[java.util.Map[String, AnyRef]]
) extends DataWriterFactory {

  override def createWriter(partitionId: Int, taskId: Long): DataWriter[InternalRow] = new Neo4jDataWriter(
    jobId,
    partitionId,
    structType,
    saveMode,
    options,
    scriptResult
  )
}

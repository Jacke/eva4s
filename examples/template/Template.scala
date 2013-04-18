package org.eva4s
package foo

object Foo {
  type Genome  = Int
  type Problem = Int
}

import Foo._

class Foo(val problem: Problem) extends Evolutionary[Genome,Problem]
  with Recombination[Genome,Problem]
  with Mutation[Genome,Problem] {

  override def ancestor: Genome =
    sys error "unimplemented"

  override def fitness(genome: Genome): Double =
    sys error "unimplemented"

  override def mutate(genome: Genome): Genome =
    sys error "unimplemented"

  override def recombine(g1: Genome, g2: Genome): Seq[Genome] =
    sys error "unimplemented"

}

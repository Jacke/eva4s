/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  Copyright  ©  2012  Nils Foken, Christian Krause                                             *
 *                                                                                               *
 *  Nils Foken        <nils.foken@it2009.ba-leipzig.de>                                          *
 *  Christian Krause  <kizkizzbangbang@googlemail.com>                                           *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  This file is part of 'eva4s'.                                                                *
 *                                                                                               *
 *  This project is free software: you can redistribute it and/or modify it under the terms      *
 *  of the GNU General Public License as published by the Free Software Foundation, either       *
 *  version 3 of the License, or any later version.                                              *
 *                                                                                               *
 *  This project is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;    *
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    *
 *  See the GNU General Public License for more details.                                         *
 *                                                                                               *
 *  You should have received a copy of the GNU General Public License along with this project.   *
 *  If not, see <http://www.gnu.org/licenses/>.                                                  *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


package org.eva4s
package solver

import language.postfixOps

import math._

import scalay.collection._

object Equation {

  /** Returns the [[http://www-optima.amp.i.kyoto-u.ac.jp/member/student/hedar/Hedar_files/TestGO_files/Page295.htm Ackley function]]. */
  val ackley: BoundedEquation = new BoundedEquation {
    def apply(xs: Vector[Double]) = {
      val b = - 0.2 * sqrt((xs map { x ⇒ x*x } sum) / xs.size)
      val d = exp(((xs map { x ⇒ cos(2 * Pi * x) } sum) / xs.size))

      20 + E - 20*exp(b) - d
    }

    def lower = -20
    def upper =  30

    override def toString = "Ackley Function"
  }

  /** Returns the [[http://mathworld.wolfram.com/GriewankFunction.html Griewank function]]. */
  val griewank: BoundedEquation = new BoundedEquation {
    def apply(xs: Vector[Double]) = {
      val a = xs.view map { pow(_,2) } sum
      val b = xs.view.zipWithIndex map {
        case (x,i) ⇒ cos(x / sqrt(i+1))
      } product

      1 + a/4000 - b
    }

    def lower = -512
    def upper =  511

    override def toString = "Griewank Function"
  }

  val alphans: BoundedEquation = new BoundedEquation {
    def apply(xs: Vector[Double]) = {
      val n = xs.size

      sqrt {
        xs.zipWithIndex.foldLeft(0.0) { (acc,xi) ⇒
          val (x,i) = xi

          val fi = if (i + 1 == n) xs.product - 1 else x + xs.sum - n - 1

          pow(fi, 2)
        }
      }
    }

    def lower = -5
    def upper =  5

    override def toString = "Alphas"
  }

}

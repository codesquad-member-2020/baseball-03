//
//  FieldView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

@IBDesignable class FieldView: UIView {
    
    static var isRotate = true
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    override func draw(_ rect: CGRect) {
        setupFieldPath()
    }
    
    private func setupFieldPath() {
        let side = sqrt(pow(self.frame.height / 2, 2) + pow(self.frame.height / 2, 2))
        let center = CGPoint(x: self.frame.width / 2, y: self.frame.height / 2)
            
        let square = UIBezierPath(roundedRect: CGRect(origin: CGPoint(x: -side, y: -side), size: CGSize(width: side * 2, height: side * 2)), cornerRadius: 20)
        UIColor(named: "FieldColor")?.setFill()
        square.rotate(translate: center, angle: 45)
        square.fill()
        
        let radius = side
        let circleCenter = CGPoint(x: side * 0.8, y: side * 0.8)
        let basePath = UIBezierPath()
        basePath.move(to: circleCenter)
        basePath.addArc(withCenter: circleCenter, radius: radius, startAngle: 180 * .pi / 180, endAngle: 270 * .pi / 180, clockwise: true)
        basePath.close()
        UIColor(named: "GroundColor")?.setFill()
        basePath.rotate(translate: center, angle: 45)
        basePath.fill()
        
        let fieldSide = side * 0.6
        let fieldCenter = CGPoint(x: circleCenter.x - fieldSide / 2, y: circleCenter.y - fieldSide / 2)
        let field = UIBezierPath(rect: CGRect(origin: CGPoint(x: circleCenter.x - fieldSide, y: circleCenter.y - fieldSide), size: CGSize(width: fieldSide, height: fieldSide)))
        UIColor(named: "FieldColor")?.setFill()
        field.rotate(translate: center, angle: 45)
        field.fill()
        
        UIColor(named: "GroundColor")?.set()
        
        let baseRadius = radius * 0.15
        let firstBaseCircle = UIBezierPath()
        let firstBaseCenter = CGPoint(x: circleCenter.x, y: circleCenter.y - fieldSide)
        firstBaseCircle.move(to: firstBaseCenter)
        firstBaseCircle.addArc(withCenter: firstBaseCenter, radius: baseRadius, startAngle: 90 * .pi / 180, endAngle: 270 * .pi / 180, clockwise: true)
        firstBaseCircle.close()
        firstBaseCircle.rotate(translate: center, angle: 45)
        firstBaseCircle.fill()
        firstBaseCircle.stroke()
        
        let secondBaseCircle = UIBezierPath()
        let secondBaseCenter = CGPoint(x: circleCenter.x - fieldSide, y: circleCenter.y - fieldSide)
        secondBaseCircle.move(to: secondBaseCenter)
        secondBaseCircle.addArc(withCenter: secondBaseCenter, radius: baseRadius, startAngle: 0 * .pi / 180, endAngle: 90 * .pi / 180, clockwise: true)
        secondBaseCircle.close()
        secondBaseCircle.rotate(translate: center, angle: 45)
        secondBaseCircle.fill()
        secondBaseCircle.stroke()
        
        let thirdBaseCircle = UIBezierPath()
        let thirdBaseCenter = CGPoint(x: circleCenter.x - fieldSide, y: circleCenter.y)
        thirdBaseCircle.move(to: thirdBaseCenter)
        thirdBaseCircle.addArc(withCenter: thirdBaseCenter, radius: baseRadius, startAngle: -90 * .pi / 180, endAngle: 0 * .pi / 180, clockwise: true)
        thirdBaseCircle.close()
        thirdBaseCircle.rotate(translate: center, angle: 45)
        thirdBaseCircle.fill()
        thirdBaseCircle.stroke()
        
        UIColor.white.setStroke()
        
        let leftLinePath = UIBezierPath()
        leftLinePath.lineWidth = 4
        leftLinePath.move(to: circleCenter)
        leftLinePath.addLine(to: CGPoint(x: -side, y: circleCenter.y))
        leftLinePath.close()
        leftLinePath.rotate(translate: center, angle: 45)
        leftLinePath.stroke()
        
        let rightLinePath = UIBezierPath()
        rightLinePath.lineWidth = 4
        rightLinePath.move(to: circleCenter)
        rightLinePath.addLine(to: CGPoint(x: circleCenter.x, y: -side))
        rightLinePath.close()
        rightLinePath.rotate(translate: center, angle: 45)
        rightLinePath.stroke()
        
        UIColor(named: "GroundColor")?.setStroke()
        leftLinePath.apply(CGAffineTransform(translationX: 0, y: 7))
        leftLinePath.lineWidth = 8
        leftLinePath.stroke()
        
        rightLinePath.apply(CGAffineTransform(translationX: 7, y: 0))
        rightLinePath.lineWidth = 8
        rightLinePath.stroke()
        
        UIColor.white.setStroke()
        field.lineWidth = 2
        field.stroke()
        
        let homeBaseCircle = UIBezierPath()
        let homeBaseCenter = CGPoint(x: circleCenter.x, y: circleCenter.y)
        homeBaseCircle.addArc(withCenter: homeBaseCenter, radius: baseRadius, startAngle: -90 * .pi / 180, endAngle: 180 * .pi / 180, clockwise: true)
        homeBaseCircle.rotate(translate: center, angle: 45)
        homeBaseCircle.lineWidth = 6
        homeBaseCircle.stroke()
        homeBaseCircle.restoreRotate(translate: center, angle: 45)
        homeBaseCircle.addArc(withCenter: homeBaseCenter, radius: baseRadius, startAngle: 180 * .pi / 180, endAngle: 270 * .pi / 180, clockwise: true)
        homeBaseCircle.rotate(translate: center, angle: 45)
        homeBaseCircle.close()
        homeBaseCircle.fill()
        
        UIColor.white.set()

        let baseSide = side / 20
        let firstBase = UIBezierPath(rect: CGRect(origin: firstBaseCenter, size: CGSize(width: baseSide, height: baseSide)))
        firstBase.apply(CGAffineTransform(translationX: -baseSide , y: 0))
        firstBase.rotate(translate: center, angle: 45)
        firstBase.fill()
        
        let secondBase = UIBezierPath(rect: CGRect(origin: secondBaseCenter, size: CGSize(width: baseSide, height: baseSide)))
        secondBase.rotate(translate: center, angle: 45)
        secondBase.fill()
        
        let thirdBase = UIBezierPath(rect: CGRect(origin: thirdBaseCenter, size: CGSize(width: baseSide, height: baseSide)))
        thirdBase.apply(CGAffineTransform(translationX: 0, y: -baseSide))
        thirdBase.rotate(translate: center, angle: 45)
        thirdBase.fill()
        
        let homeBase = drawSquare(center: homeBaseCenter, xSide: baseSide, ySide: baseSide * 1.5)
        homeBase.apply(CGAffineTransform(translationX: -homeBaseCenter.x, y: -homeBaseCenter.y))
        homeBase.apply(CGAffineTransform(rotationAngle: 45 * .pi / 180))
        homeBase.apply(CGAffineTransform(translationX: homeBaseCenter.x, y: homeBaseCenter.y))
        homeBase.apply(CGAffineTransform(translationX: -baseSide / 4, y: -baseSide / 4))
        homeBase.rotate(translate: center, angle: 45)
        homeBase.fill()
        
        let homeBaseBottom = UIBezierPath()
        homeBaseBottom.move(to: CGPoint(x: homeBaseCenter.x + baseSide / 2, y: homeBaseCenter.y + baseSide * 1.5 / 2))
        homeBaseBottom.addLine(to: CGPoint(x: homeBaseCenter.x + baseSide / 2, y: homeBaseCenter.y - baseSide * 1.5 / 2))
        homeBaseBottom.addLine(to: CGPoint(x: homeBaseCenter.x + baseSide / 6 + baseSide, y: homeBaseCenter.y))
        homeBaseBottom.apply(CGAffineTransform(translationX: -homeBaseCenter.x, y: -homeBaseCenter.y))
        homeBaseBottom.apply(CGAffineTransform(rotationAngle: 45 * .pi / 180))
        homeBaseBottom.apply(CGAffineTransform(translationX: homeBaseCenter.x, y: homeBaseCenter.y))
        homeBaseBottom.apply(CGAffineTransform(translationX: -baseSide / 4, y: -baseSide / 4))
        homeBaseBottom.rotate(translate: center, angle: 45)
        homeBaseBottom.close()
        homeBaseBottom.fill()
        
        UIColor(named: "GroundColor")?.setFill()
        let pitcherMount = UIBezierPath()
        let pitcherMountRadius = baseRadius * 0.7
        pitcherMount.addArc(withCenter: fieldCenter, radius: pitcherMountRadius, startAngle: 0, endAngle: 360 * .pi / 180, clockwise: true)
        pitcherMount.rotate(translate: center, angle: 45)
        pitcherMount.fill()
        
        let pitcher = UIBezierPath()
        pitcher.lineWidth = 6
        pitcher.move(to: CGPoint(x: fieldCenter.x - pitcherMountRadius / 2, y: fieldCenter.y))
        pitcher.addLine(to: CGPoint(x: fieldCenter.x + pitcherMountRadius / 2, y: fieldCenter.y))
        pitcher.rotate(axis: fieldCenter, angle: -45)
        pitcher.rotate(translate: center, angle: 45)
        pitcher.stroke()
    }
    
    private func drawSquare(center: CGPoint, xSide: CGFloat, ySide: CGFloat, lineWidth: CGFloat = 1) -> UIBezierPath {
        let startX = center.x - xSide / 2
        let startY = center.y - ySide / 2
        let square = UIBezierPath()
        
        square.lineWidth = lineWidth
        square.move(to: CGPoint(x: startX, y: startY))
        square.addLine(to: CGPoint(x: startX + xSide, y: startY))
        square.addLine(to: CGPoint(x: startX + xSide, y: startY + ySide))
        square.addLine(to: CGPoint(x: startX, y: startY + ySide))
        square.close()
        
        return square
    }
    
    private func drawCircleAtRectEdges(center: CGPoint, side: CGFloat, radius: CGFloat, lineWidth: CGFloat = 1) -> UIBezierPath {
        let circle = UIBezierPath()
        circle.addArc(withCenter: center, radius: radius, startAngle: 0, endAngle: 360, clockwise: true)
        circle.lineWidth = lineWidth
        circle.close()
        return circle
    }
}

extension UIBezierPath {
    func rotate(translate: CGPoint, angle: CGFloat) {
        if FieldView.isRotate {
            self.apply(CGAffineTransform(rotationAngle: angle * .pi / 180))
            self.apply(CGAffineTransform(translationX: translate.x, y: 0))
        }
    }
    
    func rotate(axis: CGPoint, angle: CGFloat) {
        if FieldView.isRotate {
            self.apply(CGAffineTransform(translationX: -axis.x, y: -axis.y))
            self.apply(CGAffineTransform(rotationAngle: angle * .pi / 180))
            self.apply(CGAffineTransform(translationX: axis.x, y: axis.y))
        }
    }
    
    func restoreRotate(translate: CGPoint, angle: CGFloat) {
        if FieldView.isRotate {
            self.apply(CGAffineTransform(translationX: -translate.x, y: 0))
            self.apply(CGAffineTransform(rotationAngle: -angle * .pi / 180))
        }
    }
}

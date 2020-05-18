import React from 'react';
import groundImg from '../image/ground.png';
import grassImg from '../image/grass.png';
import sandImg from '../image/sand.png';
import baseBallStadiumImg from '../image/baseBallStadium.jpg';

class GamePlayView_canvas extends React.Component {
    componentDidMount() {
        const canvas = this.refs.canvas;
        const ctx = canvas.getContext("2d");
        const groundImage = this.refs.ground;
        const grassImage = this.refs.grass;
        const sandImage = this.refs.sand;
        const baseBallStadiumImage = this.refs.baseBallStadium;

        // ctx.fillStyle = 'green';
        // ctx.fillRect(0, 0, 1200, 500);

        groundImage.onload = () => {
            const groundPattern = ctx.createPattern(groundImage, 'repeat');
            ctx.fillStyle = groundPattern;
            ctx.fillRect(0, 0, canvas.width, canvas.height);
        }

        baseBallStadiumImage.onload = () => {
            ctx.drawImage(baseBallStadiumImage, 0, 0, baseBallStadiumImage.width, baseBallStadiumImage.width);
            // ctx.fillRect(0, 200, 900, 400);
        }
        // grassImage.onload = () => {
        //     const grassPattern = ctx.createPattern(grassImage, 'repeat');
        //     ctx.fillStyle = grassPattern;
        //     ctx.beginPath();
        //     ctx.arc(250, 420, 350, -Math.PI * 3 / 4, -Math.PI / 4);
        //     ctx.lineTo(250, 420);
        //     ctx.closePath();
        //     ctx.fill();
        // }

        // sandImage.onload = () => {
        //     const sandPattern = ctx.createPattern(sandImage, 'repeat');
        //     ctx.fillStyle = sandPattern;
        //     ctx.beginPath();
        //     ctx.arc(250, 420, 200, -Math.PI * 3 / 4, -Math.PI / 4);
        //     ctx.lineTo(250, 420);
        //     ctx.arc(250, 420, 30, -Math.PI * 3 / 4, Math.PI * 5 / 4);
        //     ctx.closePath();
        //     ctx.fill();

        //     ctx.strokeStyle = 'white';
        //     ctx.lineWidth = 3;
        //     ctx.beginPath();
        //     ctx.arc(250, 420, 30, -Math.PI / 4, Math.PI * 5 / 4);
        //     ctx.stroke();
        // }

        // img.src = "ground.png";
        // img.src = "https://topclass.chosun.com/news_img/1411/1411_100.jpg";

    }
    render() {
        return (
            // <div style={{ backgroundImage: `url( ${groundImg} )`, backgroundSize: '85px', backgroundRepeat: 'repeat' }}>
            <div>
                {/* <div style={{ width: '1500px', height: '1500px' }}> */}
                <canvas ref="canvas" width='1920px' height='1080px' style={{ width: '-webkit-fill-available', height: '-webkit-fill-available' }} />
                <img ref="ground" src={groundImg} style={{ width: '50px', display: 'none' }} />
                <img ref="grass" src={grassImg} style={{ width: '50px', display: 'none' }} />
                <img ref="sand" src={sandImg} style={{ width: '50px', display: 'none' }} />
                <img ref="baseBallStadium" src={baseBallStadiumImg} style={{ display: 'none' }} />
                {/* <img ref="images" src="https://topclass.chosun.com/news_img/1411/1411_100.jpg" className="hidden" style={{ width: '50px', display: 'none' }} /> */}
            </div>
        )
    }
}
export default GamePlayView_canvas

import React from 'react'
import NavBar from './NavBar'
import Image from 'next/image'
import axios from 'axios'
import { useRouter } from 'next/router'




// const addToCart=async(e)=>{
//     const userId=localStorage.getItem("userId");
//      console.log("UserID",userId)
//     const shoeId=e.target.value;
//     console.log(shoeId)
//     const formData=new FormData();
//     formData.append("userId",userId);
//     formData.append("shoeDetails",shoeId);
//     try {
//    const response=await axios.post("http://localhost:8080/addToCart",formData,{
//         headers:{
//             'Content-Type':'multipart/form-data',
//         }
//       });
//       const data=await response.data
    
//       router.push({
//         pathname:'/Cart',
//       });
    
    
      
//     } catch (error) {
//       console.log(error);
//     } 

    
     
      
  
    
//   }

function view({shoeData}) {
    const router= useRouter();

    const handleAddToCart= async (e)=>{
        const shoeId=e.target.value;
        const userId=localStorage.getItem("userId")
        const formData=new FormData();
        formData.append("userId",userId);
        formData.append("shoeDetails",shoeId)
        try {
               const response=await axios.post("http://localhost:8080/addToCart",formData,{
                    headers:{
                        'Content-Type':'multipart/form-data',
                    }
                  });
                  const data=await response.data
                
                  router.push({
                    pathname:'/Cart',
                  });
                
                
                  
                } catch (error) {
                  console.log(error);
                } 


      


    }


    console.log(shoeData)
  return (
    <div className='container-fluid'>
        <NavBar></NavBar>
        <div className='row my-5 d-flex flex-column-reverse flex-md-row'>
            <div className='col-md-4'>
              <Image src={shoeData.shoeDisplayPicture} alt='shoe Picture' height={550} width={500}></Image>
            </div>
            <div className='col-md-4'>
                <Image src={shoeData.shoeExtraPicture1} alt='shoePicture' height={550} width={500}></Image>
            </div>
            <div className='col-sm-4'> 
              <div className='my-4'>
                <h2>{shoeData.shoeBrand.brandName}</h2>
                <p className=''>{shoeData.shoeName}</p>
                <br></br>
                <h4 className=''>{`Rs.${shoeData.shoePrice}`}</h4>
                <p>
                {shoeData.shoeDescription}
                </p>
                <div className='row d-flex justify-content-center'>
                    <p>Colors</p>
                    <ul>
                        <li>{shoeData.shoeColor}</li>
                    </ul>
                    <p>sizes</p>
                    <ul>
                        <li>S</li>
                        <li>X</li>
                        <li>XL</li>
                        <li>XXL</li>
                    </ul>

                </div>

                <button className='btn btn-dark' value={shoeData.shoeId} onClick={handleAddToCart} id={`btn${shoeData.shoeId}`}><label className='bi bi-bag' htmlFor={`btn${shoeData.shoeId}`}> Add To Cart</label></button>


              </div>
            </div>
            
        </div>

        <div className='row'>
            <div className='col-md-4'>
                <Image src={shoeData.shoeExtraPicture2} height={550} width={500} alt='shoePicture'></Image>
            </div>
            <div className='col-md-4'>
                <Image src={shoeData.shoeExtraPicture3} height={550} width={500 } alt='shoePicture'></Image>
            </div>
        </div>



    </div>
  )
}






export async function getServerSideProps(context){
    const {shoeId}=context.query;
    const response=await axios.get(`http://localhost:8080/getShoe/${shoeId}`)
    const shoeData= await response.data

    return {
       props:{shoeData}
    }
    

}

export default view

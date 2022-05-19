import { StyleSheet, Dimensions } from "react-native";

const styles = StyleSheet.create({
    container:{
        margin:0,
        left:10,
        height:140,
        flexDirection:"row",
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 7,
        },
        shadowOpacity: 0.43,
        shadowRadius: 9.51,

        elevation: 15,
    },
    innerContainer:{
        flexDirection:"row",
        backgroundColor:"white",
        borderRadius:10,
        overflow:"hidden"
    },
    image:{
        height:'100%',
        aspectRatio: 1 / 1,
        resizeMode:'cover',
        borderRadius:2
    },
    bedrooms:{
        marginVertical:10,
        fontSize:14,
        color:'#5b5b5b'
    },
    description:{
        fontSize:15,
        lineHeight:26,
    },
    prices:{
        fontSize:15,
        marginVertical:10
    },
    oldPrice:{
        color:"#5b5b5b",
        textDecorationLine:'line-through',
    },
    newPrice:{
        fontWeight:'bold',
        
    },
    totalPrice:{
        color:'#5b5b5b',
        textDecorationLine:'underline'
    }
})


export default styles;
import { StyleSheet, Dimensions } from "react-native";

const styles = StyleSheet.create({
    image : {
        width:'100%',
        height:"90%",
        resizeMode:'cover',
        justifyContent: 'center'
    },
    title: {
        fontSize:60,
        fontWeight:'bold',
        color:'white',
        width:'60%',
        marginLeft:"5%"
    },
    button:{
        backgroundColor:"#fff",
        width:200,
        height:40,
        borderRadius:15,
        marginLeft:"5%",
        marginTop:"5%",
        justifyContent:'center',
        alignItems:'center'

    },
    buttonText:{
        fontSize:16,
        fontWeight:'bold',
    },
    searchButton:{
        backgroundColor:"#fff",
        height:60,
        width:Dimensions.get('screen').width - 20,
        borderRadius:30,
        marginHorizontal:10,
        flexDirection:'row',
        // justifyContent:'center',
        alignItems:'center',
        position:"absolute",
        top:40,
        zIndex:100,        
    },
    searchButtonText:{
        fontSize:16,
        marginLeft:10,
        fontWeight:'bold',
    }
})

export default styles;